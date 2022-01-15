package guldilin.service;

import guldilin.dto.CityDTO;
import guldilin.dto.CityListDTO;
import guldilin.exceptions.EntryNotFound;
import guldilin.exceptions.ErrorMessage;
import guldilin.exceptions.StorageServiceRequestException;
import guldilin.util.ClientFactoryBuilder;
import guldilin.util.ServiceDiscoveryClientFactory;
import lombok.SneakyThrows;

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

    public CityServiceImpl() {
        this.client = ClientFactoryBuilder.getClient();
    }

    @SneakyThrows
    @Override
    public CityDTO getById(Long id) {
        Response response = client.target(ServiceDiscoveryClientFactory.getStorageApiUrl() + "/api/city/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            throw new EntryNotFound(id, ErrorMessage.CITY_NOT_FOUND);
        }
        if (response.getStatus() > 300) throw new StorageServiceRequestException();
        return response.readEntity(CityDTO.class);
    }

    @SneakyThrows
    @Override
    public CityDTO getCityWithMaxPopulation() {
        List<CityDTO> cityDTOList = client.target(ServiceDiscoveryClientFactory.getStorageApiUrl() + "/api/city?sorting=-population&limit=1")
                .request(MediaType.APPLICATION_JSON)
                .get()
                .readEntity(CityListDTO.class)
                .getResults();
        if (cityDTOList.size() == 0) throw new EntryNotFound(ErrorMessage.CITY_NOT_FOUND);
        return cityDTOList.get(0);
    }
}
