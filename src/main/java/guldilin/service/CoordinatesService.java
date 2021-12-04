package guldilin.service;

import guldilin.dto.CoordinatesDTO;
import guldilin.exceptions.EntryNotFound;
import guldilin.exceptions.ErrorMessage;
import guldilin.exceptions.StorageServiceRequestException;
import guldilin.util.ClientFactoryBuilder;
import lombok.SneakyThrows;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CoordinatesService {
    private final Client client;
    private final String storageServiceUrl;

    public CoordinatesService() {
        this.client = ClientFactoryBuilder.getClient();
        this.storageServiceUrl = ClientFactoryBuilder.getStorageServiceUrl();
    }

    @SneakyThrows
    public CoordinatesDTO getById(Long id) {
        System.out.println("GET " + storageServiceUrl + "/api/coordinates/" + id);
        Response response = client.target(storageServiceUrl + "/api/coordinates/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get();
        System.out.println("Response status = " + response.getStatus());
        if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            throw new EntryNotFound(id, ErrorMessage.COORDINATES_NOT_FOUND);
        }
        if (response.getStatus() > 300) throw new StorageServiceRequestException();
        CoordinatesDTO dto = response.readEntity(CoordinatesDTO.class);
        System.out.println("READ city dto " + dto);
        return dto;
    }

    public Double getDistanceBetween(CoordinatesDTO from, CoordinatesDTO to) {
        return Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2));
    }
}
