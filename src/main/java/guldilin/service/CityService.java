package guldilin.service;

import guldilin.dto.CityDTO;
import guldilin.dto.CityListDTO;
import guldilin.exceptions.StorageServiceRequestException;
import guldilin.util.ClientFactoryBuilder;
import lombok.SneakyThrows;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

public class CityService {
    private final Client client;
    private final String storageServiceUrl;

    public CityService() {
        this.client = ClientFactoryBuilder.getClient();
        this.storageServiceUrl = System.getenv("STORAGE_SERVICE_URL");

    }

    @SneakyThrows
    public CityDTO getById(Long id) {
        try {
            return client.target(storageServiceUrl + "/api/city")
                    .path(String.valueOf(id))
                    .request(MediaType.APPLICATION_JSON)
                    .get()
                    .readEntity(CityDTO.class);
        } catch (Exception e) {
            throw new StorageServiceRequestException("Cannot do request");
        }
    }

    public CityDTO getCityWithMaxPopulation() {
        return client.target(storageServiceUrl + "/api/city?sorting=-population&limit=1")
                .request(MediaType.APPLICATION_JSON)
                .get()
                .readEntity(CityListDTO.class)
                .getResults()
                .get(0);
    }
}
