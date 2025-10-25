package pe.com.galaxy.training.api_rest.services.ubicacion;

import org.springframework.stereotype.Service;
import pe.com.galaxy.training.api_rest.dto.ubicacion.DepartamentoResponseDto;
import pe.com.galaxy.training.api_rest.mappers.ubicacion.DepartamentoMapper;
import pe.com.galaxy.training.api_rest.repository.ubicacion.DepartamentoRepository;

import java.util.List;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final DepartamentoMapper departamentoMapper;

    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository, DepartamentoMapper departamentoMapper) {
        this.departamentoRepository = departamentoRepository;
        this.departamentoMapper = departamentoMapper;
    }

    @Override
    public List<DepartamentoResponseDto> findAll() throws DepartamentoException {
        return departamentoMapper.ListToResponseList(departamentoRepository.findAll());
    }
}