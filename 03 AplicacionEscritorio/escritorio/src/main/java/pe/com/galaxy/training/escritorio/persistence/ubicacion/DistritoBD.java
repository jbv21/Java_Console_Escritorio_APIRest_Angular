package pe.com.galaxy.training.escritorio.persistence.ubicacion;

import pe.com.galaxy.training.escritorio.bean.ubicacion.Distrito;
import pe.com.galaxy.training.escritorio.persistence.util.PersistenciaException;

import java.util.List;

public interface DistritoBD {

    List<Distrito> listar(Distrito obj) throws PersistenciaException;

    Distrito seleccionar(Distrito obj) throws PersistenciaException;
}
