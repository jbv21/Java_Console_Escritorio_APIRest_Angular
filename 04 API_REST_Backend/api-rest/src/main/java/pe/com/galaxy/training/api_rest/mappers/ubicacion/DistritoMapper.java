package pe.com.galaxy.training.api_rest.mappers.ubicacion;

import org.mapstruct.Mapper;
import pe.com.galaxy.training.api_rest.dto.ubicacion.DistritoRequestDto;
import pe.com.galaxy.training.api_rest.dto.ubicacion.DistritoResponseDto;
import pe.com.galaxy.training.api_rest.entity.ubicacion.Distrito;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DistritoMapper {
    Distrito toEntity(DistritoRequestDto obj);
    List<DistritoResponseDto> listToResponseList(List<Distrito> obj);
}
