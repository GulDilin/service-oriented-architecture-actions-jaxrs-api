package guldilin.service;

import guldilin.dto.CoordinatesDTO;

import java.io.Serializable;

public interface CoordinatesService extends Serializable {
    CoordinatesDTO getById(Long id) throws Exception;

    Double getDistanceBetween(CoordinatesDTO from, CoordinatesDTO to) throws Exception;
}
