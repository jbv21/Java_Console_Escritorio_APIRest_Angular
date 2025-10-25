package pe.com.galaxy.training.escritorio.persistence.escuela;

import pe.com.galaxy.training.escritorio.bean.escuela.EscuelaConduccion;
import pe.com.galaxy.training.escritorio.persistence.util.PersistenciaException;

import java.util.List;

public interface EscuelaConduccionBD {
    List<EscuelaConduccion> listar(EscuelaConduccion obj) throws PersistenciaException;

    EscuelaConduccion seleccionar(EscuelaConduccion obj) throws PersistenciaException;

    Boolean insertar(EscuelaConduccion obj) throws PersistenciaException;

    Boolean actualizar(EscuelaConduccion obj) throws PersistenciaException;

    Boolean eliminar(EscuelaConduccion obj) throws PersistenciaException;
}
