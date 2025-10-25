package pe.com.galaxy.training.console.persistence.ubicacion;

import pe.com.galaxy.training.console.bean.ubicacion.Provincia;
import pe.com.galaxy.training.console.persistence.util.PersistenciaException;

import java.util.List;

public interface ProvinciaBD {
    List<Provincia> listar(Provincia obj) throws PersistenciaException;
}
