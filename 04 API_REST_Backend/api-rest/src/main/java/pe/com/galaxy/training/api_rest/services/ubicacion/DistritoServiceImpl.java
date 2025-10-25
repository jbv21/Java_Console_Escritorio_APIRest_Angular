package pe.com.galaxy.training.api_rest.services.ubicacion;

import org.springframework.stereotype.Service;
import pe.com.galaxy.training.api_rest.dto.ubicacion.DistritoRequestDto;
import pe.com.galaxy.training.api_rest.dto.ubicacion.DistritoResponseDto;
import pe.com.galaxy.training.api_rest.entity.ubicacion.Distrito;
import pe.com.galaxy.training.api_rest.mappers.ubicacion.DistritoMapper;
import pe.com.galaxy.training.api_rest.repository.ubicacion.DistritoRepository;

import java.util.List;

@Service
public class DistritoServiceImpl implements DistritoService {

    private final DistritoRepository distritoRepository;
    private final DistritoMapper distritoMapper;

    public DistritoServiceImpl(DistritoRepository distritoRepository, DistritoMapper distritoMapper) {
        this.distritoRepository = distritoRepository;
        this.distritoMapper = distritoMapper;
    }

    @Override
    public List<DistritoResponseDto> findAll(DistritoRequestDto obj) throws DistritoException {
        Distrito objEnt = distritoMapper.toEntity(obj);
        return distritoMapper.listToResponseList( distritoRepository.findAll(objEnt.getIdDepartamento(), objEnt.getIdProvincia()) );
    }
}
