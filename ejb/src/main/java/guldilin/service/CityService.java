package guldilin.service;

import guldilin.dto.CityDTO;

public interface CityService {
    CityDTO getById(Long id);
    CityDTO getCityWithMaxPopulation();
}
