package pe.com.galaxy.training.api_rest.mappers.escuela;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import pe.com.galaxy.training.api_rest.dto.escuela.EscuelaConduccionRequestDTO;
import pe.com.galaxy.training.api_rest.dto.escuela.EscuelaConduccionResponseDTO;
import pe.com.galaxy.training.api_rest.entity.escuela.EscuelaConduccion;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EscuelaConduccionMapper {
    EscuelaConduccion toEntity(EscuelaConduccionRequestDTO obj);
    EscuelaConduccionResponseDTO toResponse(EscuelaConduccion obj);
    default Page<EscuelaConduccionResponseDTO> tooResponseList(Page<EscuelaConduccion> obj) {
        return obj.map(this::toResponse);
    }
    List<EscuelaConduccionResponseDTO> tooResponseList(List<EscuelaConduccion> obj);
}
