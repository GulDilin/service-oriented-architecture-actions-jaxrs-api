package guldilin.service;

import guldilin.dto.CityDTO;
import guldilin.exceptions.ArgumentFormatException;
import guldilin.exceptions.EntryNotFound;
import guldilin.exceptions.StorageServiceRequestException;

import javax.ejb.Remote;
import java.io.Serializable;

@Remote
public interface CityService extends Serializable {
    CityDTO getById(Long id) throws EntryNotFound, ArgumentFormatException, NullPointerException, StorageServiceRequestException;
    CityDTO getCityWithMaxPopulation() throws EntryNotFound, ArgumentFormatException, NullPointerException, StorageServiceRequestException;
}