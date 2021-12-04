package guldilin.controllers;

import guldilin.dto.CoordinatesDTO;
import guldilin.dto.LengthDTO;
import guldilin.exceptions.ArgumentFormatException;
import guldilin.exceptions.ErrorMessage;
import guldilin.service.CityService;
import guldilin.service.CoordinatesService;
import lombok.SneakyThrows;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Path("/calculate")
public class CalculationResource {
    private final CoordinatesService coordinatesService;
    private final CityService cityService;

    public CalculationResource() {
        this.cityService = new CityService();
        this.coordinatesService = new CoordinatesService();
    }

    @GET
    @Path("/length/{id1}/{id2}")
    @Produces(MediaType.APPLICATION_JSON)
    @SneakyThrows
    public LengthDTO calculateDistanceBetweenCities(
            @PathParam("id1") String id1,
            @PathParam("id2") String id2
    ) {
        long lid1, lid2;
        try {
            lid1 = Long.parseLong(id1);
        } catch (NumberFormatException e) {
            throw new ArgumentFormatException("id1", ErrorMessage.IS_INTEGER);
        }
        try {
            lid2 = Long.parseLong(id2);
        } catch (NumberFormatException e) {
            throw new ArgumentFormatException("id2", ErrorMessage.IS_INTEGER);
        }
        CoordinatesDTO c1 = this.coordinatesService.getById(this.cityService.getById(lid1).getCoordinates());
        CoordinatesDTO c2 = this.coordinatesService.getById(this.cityService.getById(lid2).getCoordinates());
        return new LengthDTO(this.coordinatesService.getDistanceBetween(c1, c2));
    }

    @GET
    @Path("/to-max-populated")
    @Produces(MediaType.APPLICATION_JSON)
    public LengthDTO calculateDistanceToMaxPopulated() {
        CoordinatesDTO c = this.coordinatesService.getById(this.cityService.getCityWithMaxPopulation().getCoordinates());
        return new LengthDTO(this.coordinatesService.getDistanceBetween(c,
                CoordinatesDTO.builder().x(0L).y(0).build()));
    }
}
