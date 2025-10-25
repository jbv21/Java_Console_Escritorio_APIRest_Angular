package pe.com.galaxy.training.api_rest.services.ubicacion;

import org.springframework.stereotype.Service;
import pe.com.galaxy.training.api_rest.dto.ubicacion.ProvinciaRequestDto;
import pe.com.galaxy.training.api_rest.dto.ubicacion.ProvinciaResponseDto;
import pe.com.galaxy.training.api_rest.entity.ubicacion.Provincia;
import pe.com.galaxy.training.api_rest.mappers.ubicacion.ProvinciaMapper;
import pe.com.galaxy.training.api_rest.repository.ubicacion.ProvinciaRepository;

import java.util.List;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {

    private final ProvinciaRepository provinciaRepository;
    private final ProvinciaMapper provinciaMapper;

    public ProvinciaServiceImpl(ProvinciaRepository provinciaRepository, ProvinciaMapper provinciaMapper) {
        this.provinciaRepository = provinciaRepository;
        this.provinciaMapper = provinciaMapper;
    }

    @Override
    public List<ProvinciaResponseDto> findAll(ProvinciaRequestDto obj) throws ProvinciaException {
        Provincia objEnt = provinciaMapper.toEntity(obj);
        return provinciaMapper.listToResponseList( provinciaRepository.findAll(objEnt.getIdDepartamento()) );
    }
}
