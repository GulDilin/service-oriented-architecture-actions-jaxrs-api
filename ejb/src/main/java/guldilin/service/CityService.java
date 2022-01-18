package guldilin.service;

import guldilin.dto.CityDTO;

import java.io.Serializable;

public interface CityService extends Serializable {
    CityDTO getById(Long id) throws Exception;
    CityDTO getCityWithMaxPopulation() throws Exception;
}
