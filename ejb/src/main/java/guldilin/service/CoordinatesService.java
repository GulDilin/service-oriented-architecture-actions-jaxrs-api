package guldilin.service;

import guldilin.dto.CoordinatesDTO;

public interface CoordinatesService {
    CoordinatesDTO getById(Long id);

    Double getDistanceBetween(CoordinatesDTO from, CoordinatesDTO to);
}
