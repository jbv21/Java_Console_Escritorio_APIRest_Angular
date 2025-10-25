package pe.com.galaxy.training.api_rest.mappers.ubicacion;

import org.mapstruct.Mapper;
import pe.com.galaxy.training.api_rest.dto.ubicacion.ProvinciaRequestDto;
import pe.com.galaxy.training.api_rest.dto.ubicacion.ProvinciaResponseDto;
import pe.com.galaxy.training.api_rest.entity.ubicacion.Provincia;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProvinciaMapper {
    Provincia toEntity(ProvinciaRequestDto obj);
    List<ProvinciaResponseDto> listToResponseList(List<Provincia> obj);
}
