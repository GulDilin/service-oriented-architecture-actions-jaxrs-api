package guldilin.service;

import guldilin.dto.CityDTO;
import guldilin.exceptions.ArgumentFormatException;
import guldilin.exceptions.EntryNotFound;
import guldilin.exceptions.StorageServiceRequestException;

import java.io.Serializable;

public interface CityService extends Serializable {
    CityDTO getById(Long id) throws EntryNotFound, ArgumentFormatException, NullPointerException, StorageServiceRequestException;
    CityDTO getCityWithMaxPopulation() throws EntryNotFound, ArgumentFormatException, NullPointerException, StorageServiceRequestException;
}
