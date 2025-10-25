package pe.com.galaxy.training.api_rest.services.ubicacion;

import org.springframework.stereotype.Service;
import pe.com.galaxy.training.api_rest.dto.ubicacion.DepartamentoResponseDto;
import java.util.List;

public interface DepartamentoService {

    List<DepartamentoResponseDto> findAll() throws DepartamentoException;

}
