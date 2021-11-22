package guldilin.controllers;

import guldilin.dto.CoordinatesDTO;
import guldilin.dto.LengthDTO;
import guldilin.service.CityService;
import guldilin.service.CoordinatesService;

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
    public LengthDTO calculateDistanceBetweenCities(
            @PathParam("id1") Long id1,
            @PathParam("id2") Long id2
    ) {
        CoordinatesDTO c1 = this.coordinatesService.getById(this.cityService.getById(id1).getCoordinates());
        CoordinatesDTO c2 = this.coordinatesService.getById(this.cityService.getById(id2).getCoordinates());
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
