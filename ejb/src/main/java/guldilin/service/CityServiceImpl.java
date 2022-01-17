package guldilin.service;

import guldilin.dto.CityDTO;
import guldilin.dto.CityListDTO;
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
import java.util.List;

@Stateless
@Remote(CityService.class)
public class CityServiceImpl implements CityService {
    private final Client client;
    private final String storageApiUrl;

    public CityServiceImpl() {
        this.client = ClientFactoryBuilder.getClient();
        this.storageApiUrl = ServiceDiscoveryClientFactory.getStorageApiUrl();
    }

    @Override
    public CityDTO getById(Long id) throws Exception {
        Response response = client.target(storageApiUrl + "/api/city/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            throw ExceptionsUtils.serializeRemoteException(new EntryNotFound(id, ErrorMessage.CITY_NOT_FOUND));
        }
        if (response.getStatus() > 300) throw ExceptionsUtils.serializeRemoteException(
                new StorageServiceRequestException());
        return response.readEntity(CityDTO.class);
    }

    @Override
    public CityDTO getCityWithMaxPopulation() throws Exception {
        List<CityDTO> cityDTOList = client.target(storageApiUrl + "/api/city?sorting=-population&limit=1")
                .request(MediaType.APPLICATION_JSON)
                .get()
                .readEntity(CityListDTO.class)
                .getResults();
        if (cityDTOList.size() == 0) throw ExceptionsUtils.serializeRemoteException(
                new EntryNotFound(ErrorMessage.CITY_NOT_FOUND));
        return cityDTOList.get(0);
    }
}
