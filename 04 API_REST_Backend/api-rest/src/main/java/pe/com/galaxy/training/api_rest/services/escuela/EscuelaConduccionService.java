package pe.com.galaxy.training.api_rest.services.escuela;

import pe.com.galaxy.training.api_rest.dto.escuela.EscuelaConduccionRequestDTO;
import pe.com.galaxy.training.api_rest.dto.escuela.EscuelaConduccionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface EscuelaConduccionService {

    Page<EscuelaConduccionResponseDTO> findAllCustomPaging(Pageable pageable, EscuelaConduccionRequestDTO obj) throws EscuelaConduccionException;

    List<EscuelaConduccionResponseDTO> findAll(EscuelaConduccionRequestDTO obj) throws EscuelaConduccionException;

    Optional<EscuelaConduccionResponseDTO> findById(EscuelaConduccionRequestDTO obj) throws EscuelaConduccionException;

    EscuelaConduccionResponseDTO save(EscuelaConduccionRequestDTO obj) throws EscuelaConduccionException;

    EscuelaConduccionResponseDTO update(EscuelaConduccionRequestDTO obj) throws EscuelaConduccionException;

    EscuelaConduccionResponseDTO updateEstado(EscuelaConduccionRequestDTO obj) throws EscuelaConduccionException;

    void delete(EscuelaConduccionRequestDTO obj) throws EscuelaConduccionException;
}
