package pe.com.galaxy.training.api_rest.mappers.ubicacion;

import org.mapstruct.Mapper;
import pe.com.galaxy.training.api_rest.dto.ubicacion.DepartamentoRequestDto;
import pe.com.galaxy.training.api_rest.dto.ubicacion.DepartamentoResponseDto;
import pe.com.galaxy.training.api_rest.entity.ubicacion.Departamento;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {
    Departamento toEntity(DepartamentoRequestDto obj);
    List<DepartamentoResponseDto> ListToResponseList(List<Departamento> obj);
}
