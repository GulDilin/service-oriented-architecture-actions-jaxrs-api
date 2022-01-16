package guldilin.service;

import guldilin.dto.CoordinatesDTO;
import guldilin.exceptions.ArgumentFormatException;
import guldilin.exceptions.EntryNotFound;
import guldilin.exceptions.StorageServiceRequestException;

import java.io.Serializable;

public interface CoordinatesService extends Serializable {
    CoordinatesDTO getById(Long id) throws EntryNotFound, ArgumentFormatException, NullPointerException, StorageServiceRequestException;

    Double getDistanceBetween(CoordinatesDTO from, CoordinatesDTO to) throws EntryNotFound, ArgumentFormatException, NullPointerException, StorageServiceRequestException;
}
