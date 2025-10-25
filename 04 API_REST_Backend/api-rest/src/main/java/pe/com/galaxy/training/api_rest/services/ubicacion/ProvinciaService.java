package pe.com.galaxy.training.api_rest.services.ubicacion;

import pe.com.galaxy.training.api_rest.dto.ubicacion.ProvinciaRequestDto;
import pe.com.galaxy.training.api_rest.dto.ubicacion.ProvinciaResponseDto;

import java.util.List;

public interface ProvinciaService {

    List<ProvinciaResponseDto> findAll(ProvinciaRequestDto obj) throws ProvinciaException;
}
