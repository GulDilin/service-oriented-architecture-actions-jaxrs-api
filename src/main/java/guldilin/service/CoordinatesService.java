package guldilin.service;

import guldilin.dto.CoordinatesDTO;
import guldilin.util.ClientFactoryBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import java.net.URLEncoder;

public class CoordinatesService {
    private final Client client;
    private final String storageServiceUrl;

    public CoordinatesService() {
        this.client = ClientFactoryBuilder.getClient();
        this.storageServiceUrl = System.getenv("STORAGE_SERVICE_URL");
    }

    public CoordinatesDTO getById(Long id) {
        return client.target(URLEncoder.encode(storageServiceUrl + "/api/coordinates/" + id))
                .request(MediaType.APPLICATION_JSON)
                .get()
                .readEntity(CoordinatesDTO.class);
    }

    public Double getDistanceBetween(CoordinatesDTO from, CoordinatesDTO to) {
        return Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2));
    }
}
