package pe.com.galaxy.training.escritorio.persistence.ubicacion;

import pe.com.galaxy.training.escritorio.bean.ubicacion.Provincia;
import pe.com.galaxy.training.escritorio.persistence.util.PersistenciaException;

import java.util.List;

public interface ProvinciaBD {
    List<Provincia> listar(Provincia obj) throws PersistenciaException;
    
    Provincia seleccionar(Provincia obj) throws PersistenciaException;
}
