package guldilin.service;

import guldilin.dto.CityDTO;
import guldilin.dto.CityListDTO;
import guldilin.exceptions.EntryNotFound;
import guldilin.exceptions.ErrorMessage;
import guldilin.exceptions.StorageServiceRequestException;
import guldilin.util.ClientFactoryBuilder;
import lombok.SneakyThrows;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CityService {
    private final Client client;
    private final String storageServiceUrl;

    public CityService() {
        this.client = ClientFactoryBuilder.getClient();
        this.storageServiceUrl = ClientFactoryBuilder.getStorageServiceUrl();

    }

    @SneakyThrows
    public CityDTO getById(Long id) {
        System.out.println("GET " + storageServiceUrl + "/api/city/" + id);
        Response response = client.target(storageServiceUrl + "/api/city/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get();
        System.out.println("Response status = " + response.getStatus());
        if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()){
            throw new EntryNotFound(id, ErrorMessage.CITY_NOT_FOUND);
        }
        if (response.getStatus() > 300) throw new StorageServiceRequestException();
        CityDTO dto = response.readEntity(CityDTO.class);
        System.out.println("READ city dto " + dto);
        return dto;
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
