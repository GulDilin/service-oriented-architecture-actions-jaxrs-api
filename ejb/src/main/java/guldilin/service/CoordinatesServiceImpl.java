package guldilin.service;

import guldilin.dto.CoordinatesDTO;
import guldilin.exceptions.EntryNotFound;
import guldilin.exceptions.ErrorMessage;
import guldilin.exceptions.StorageServiceRequestException;
import guldilin.util.ClientFactoryBuilder;
import guldilin.util.ExceptionsUtils;
import guldilin.util.ServiceDiscoveryClientFactory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Remote(CoordinatesService.class)
public class CoordinatesServiceImpl implements CoordinatesService {
    private final Client client;
    private final String storageApiUrl;


    public CoordinatesServiceImpl() {
        this.client = ClientFactoryBuilder.getClient();
        this.storageApiUrl = ServiceDiscoveryClientFactory.getStorageApiUrl();
    }

    @Override
    public CoordinatesDTO getById(Long id) throws Exception {
        Response response = client.target(storageApiUrl + "/api/coordinates/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            throw ExceptionsUtils.serializeRemoteException(new EntryNotFound(id, ErrorMessage.COORDINATES_NOT_FOUND));
        }
        if (response.getStatus() > 300) throw ExceptionsUtils.serializeRemoteException(
                new StorageServiceRequestException());
        return response.readEntity(CoordinatesDTO.class);
    }

    @Override
    public Double getDistanceBetween(CoordinatesDTO from, CoordinatesDTO to) {
        return Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2));
    }
}
