package pe.com.galaxy.training.console.persistence.ubicacion;

import pe.com.galaxy.training.console.bean.ubicacion.Departamento;
import pe.com.galaxy.training.console.persistence.util.PersistenciaException;

import java.util.List;

public interface DepartamentoBD {

    List<Departamento> listar(Departamento obj)throws PersistenciaException;
}
