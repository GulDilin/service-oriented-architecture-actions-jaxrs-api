package guldilin.service;

import guldilin.dto.CoordinatesDTO;

import javax.ejb.Remote;

@Remote
public interface CoordinatesService {
    CoordinatesDTO getById(Long id);
    Double getDistanceBetween(CoordinatesDTO from, CoordinatesDTO to);
}
