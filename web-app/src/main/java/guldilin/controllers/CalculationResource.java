package guldilin.controllers;

import guldilin.dto.CalculationBetweenCitiesArgumentDTO;
import guldilin.dto.CoordinatesDTO;
import guldilin.dto.LengthDTO;
import guldilin.exceptions.ArgumentFormatException;
import guldilin.exceptions.EntryNotFound;
import guldilin.exceptions.StorageServiceRequestException;
import guldilin.service.CityService;
import guldilin.service.CoordinatesService;
import guldilin.util.Utils;
import lombok.SneakyThrows;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

import static guldilin.util.Utils.ACTIONS_API_CALCULATION_RESOURCE;


@WebService(
        name = "CalculationResource",
        targetNamespace = ACTIONS_API_CALCULATION_RESOURCE
)
public class CalculationResource {
    private final CoordinatesService coordinatesService;
    private final CityService cityService;

    @SneakyThrows
    public CalculationResource() {
        Context context = new ContextProvider().getContext();
        String moduleName = Utils.getEnvOrPropElseDefault("CONTEXT_MODULE_NAME_EJB",
                "service-oriented-architecture-actions-jaxrs-api-ejb");
        Object refCityService = context.lookup("ejb:/" + moduleName + "/CityServiceImpl!" + CityService.class.getName());
        Object refCoordinatesService = context.lookup("ejb:/" + moduleName + "/CoordinatesServiceImpl!" + CoordinatesService.class.getName());
        this.cityService = (CityService) PortableRemoteObject.narrow(refCityService, CityService.class);
        this.coordinatesService = (CoordinatesService) PortableRemoteObject.narrow(refCoordinatesService, CoordinatesService.class);
    }

    @WebMethod
    public LengthDTO calculateDistanceBetweenCities(@WebParam(name = "arg0") CalculationBetweenCitiesArgumentDTO argument)
            throws ArgumentFormatException, StorageServiceRequestException, EntryNotFound {
        CoordinatesDTO c1 = this.coordinatesService.getById(this.cityService.getById(argument.getId1()).getCoordinates());
        CoordinatesDTO c2 = this.coordinatesService.getById(this.cityService.getById(argument.getId2()).getCoordinates());
        return new LengthDTO(this.coordinatesService.getDistanceBetween(c1, c2));
    }

    @WebMethod
    public LengthDTO calculateDistanceToMaxPopulated()
            throws StorageServiceRequestException, EntryNotFound, ArgumentFormatException {
        CoordinatesDTO c = this.coordinatesService.getById(this.cityService.getCityWithMaxPopulation().getCoordinates());
        return new LengthDTO(this.coordinatesService.getDistanceBetween(c, CoordinatesDTO.builder().x(0L).y(0).build()));
    }
}
