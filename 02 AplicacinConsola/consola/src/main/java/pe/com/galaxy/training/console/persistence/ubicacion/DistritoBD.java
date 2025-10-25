package pe.com.galaxy.training.console.persistence.ubicacion;

import pe.com.galaxy.training.console.bean.ubicacion.Distrito;
import pe.com.galaxy.training.console.persistence.util.PersistenciaException;

import java.util.List;

public interface DistritoBD {

    List<Distrito> listar(Distrito obj) throws PersistenciaException;
}
