package guldilin.service;

import guldilin.dto.CityDTO;

import javax.ejb.Remote;

@Remote
public interface CityService {
    CityDTO getById(Long id);
    CityDTO getCityWithMaxPopulation();
}
