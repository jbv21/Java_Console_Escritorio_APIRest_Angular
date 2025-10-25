package pe.com.galaxy.training.api_rest.services.ubicacion;

import pe.com.galaxy.training.api_rest.dto.ubicacion.DistritoRequestDto;
import pe.com.galaxy.training.api_rest.dto.ubicacion.DistritoResponseDto;

import java.util.List;

public interface DistritoService {
    List<DistritoResponseDto> findAll(DistritoRequestDto obj) throws DistritoException;
}
