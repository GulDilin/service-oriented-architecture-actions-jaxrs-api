package guldilin.service;

import guldilin.dto.CityDTO;
import guldilin.exceptions.ArgumentFormatException;
import guldilin.exceptions.EntryNotFound;
import guldilin.exceptions.StorageServiceRequestException;

import javax.ejb.Remote;
import java.io.Serializable;

@Remote
public interface CityService extends Serializable {
    CityDTO getById(Long id) throws ArgumentFormatException, EntryNotFound, StorageServiceRequestException;

    CityDTO getCityWithMaxPopulation() throws ArgumentFormatException, EntryNotFound, StorageServiceRequestException;
}