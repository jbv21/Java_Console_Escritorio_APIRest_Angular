package pe.com.galaxy.training.api_rest.services.escuela;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.com.galaxy.training.api_rest.dto.escuela.EscuelaConduccionRequestDTO;
import pe.com.galaxy.training.api_rest.dto.escuela.EscuelaConduccionResponseDTO;
import pe.com.galaxy.training.api_rest.entity.escuela.EscuelaConduccion;
import pe.com.galaxy.training.api_rest.mappers.escuela.EscuelaConduccionMapper;
import pe.com.galaxy.training.api_rest.repository.escuela.EscuelaConduccionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EscuelaConduccionServiceImpl implements EscuelaConduccionService {

    private final EscuelaConduccionRepository escuelaConduccionRepository;
    private final EscuelaConduccionMapper escuelaConduccionMapper;

    public EscuelaConduccionServiceImpl(
            EscuelaConduccionRepository escuelaConduccionRepository,
            EscuelaConduccionMapper escuelaConduccionMapper
    ){
        this.escuelaConduccionRepository = escuelaConduccionRepository;
        this.escuelaConduccionMapper = escuelaConduccionMapper;
    }

    @Override
    public Page<EscuelaConduccionResponseDTO> findAllCustomPaging(Pageable pageable, EscuelaConduccionRequestDTO obj) throws EscuelaConduccionException {
        EscuelaConduccion objEntity = escuelaConduccionMapper.toEntity(obj);
        return escuelaConduccionMapper
                .tooResponseList(
                        escuelaConduccionRepository
                                .findAllCustomPaging(
                                        pageable,
                                        objEntity.getIdDepartamento(),
                                        objEntity.getIdProvincia(),
                                        objEntity.getIdDistrito(),
                                        objEntity.getRuc() ) );
    }

    @Override
    public List<EscuelaConduccionResponseDTO> findAll(EscuelaConduccionRequestDTO obj) throws EscuelaConduccionException {
        EscuelaConduccion objEntity = escuelaConduccionMapper.toEntity(obj);
        return escuelaConduccionMapper
                .tooResponseList(
                        escuelaConduccionRepository
                                .findAllCustom(
                                        objEntity.getIdDepartamento(),
                                        objEntity.getIdProvincia(),
                                        objEntity.getIdDistrito(),
                                        objEntity.getRuc() ) );

    }

    @Override
    public Optional<EscuelaConduccionResponseDTO> findById(EscuelaConduccionRequestDTO obj) throws EscuelaConduccionException {
        return escuelaConduccionRepository.findById(escuelaConduccionMapper.toEntity(obj).getId()).map(escuelaConduccionMapper::toResponse);
    }

    @Override
    public EscuelaConduccionResponseDTO save(EscuelaConduccionRequestDTO obj) throws EscuelaConduccionException {
        EscuelaConduccion objEnt = escuelaConduccionMapper.toEntity(obj);
        objEnt.setActivo(1);
        return escuelaConduccionMapper.toResponse((escuelaConduccionRepository.save(objEnt)));
    }

    @Override
    public EscuelaConduccionResponseDTO update(EscuelaConduccionRequestDTO obj) throws EscuelaConduccionException {
        EscuelaConduccion objEnt = escuelaConduccionMapper.toEntity(obj);
        EscuelaConduccion objEntOrig = escuelaConduccionRepository.findById(objEnt.getId())
                .orElseThrow(() -> new RuntimeException("No existe Escuela de Conducir con el id "+objEnt.getId()));
        objEntOrig.setRuc(objEnt.getRuc());
        objEntOrig.setNombre(objEnt.getNombre());
        objEntOrig.setDireccion(objEnt.getDireccion());
        objEntOrig.setCorreo(objEnt.getCorreo());
        objEntOrig.setTelefono(objEnt.getTelefono());
        objEntOrig.setIdDepartamento(objEnt.getIdDepartamento());

        objEntOrig.setUbigeo(objEnt.getUbigeo());

        objEntOrig.setEstado(objEnt.getEstado());
        return escuelaConduccionMapper.toResponse(escuelaConduccionRepository.save(objEntOrig));
    }

    @Override
    public EscuelaConduccionResponseDTO updateEstado(EscuelaConduccionRequestDTO obj) throws EscuelaConduccionException {
        EscuelaConduccion objEnt = escuelaConduccionMapper.toEntity(obj);
        EscuelaConduccion objEntOrig = escuelaConduccionRepository.findById(objEnt.getId())
                .orElseThrow(() -> new RuntimeException("No existe Escuela de Conducir con el id "+objEnt.getId()));
        objEntOrig.setEstado(objEnt.getEstado());
        return escuelaConduccionMapper.toResponse(escuelaConduccionRepository.save(objEntOrig));
    }

    @Override
    public void delete(EscuelaConduccionRequestDTO obj) throws EscuelaConduccionException {
        EscuelaConduccion objEnt = escuelaConduccionMapper.toEntity(obj);
        EscuelaConduccion objEntOrig = escuelaConduccionRepository.findById(objEnt.getId())
                .orElseThrow(() -> new RuntimeException("No existe Escuela de Conducir con el id "+objEnt.getId()));
        objEntOrig.setActivo(0);
        escuelaConduccionMapper.toResponse(escuelaConduccionRepository.save(objEntOrig));
    }
}
