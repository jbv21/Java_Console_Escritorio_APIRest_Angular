package pe.com.galaxy.training.escritorio.persistence.ubicacion;

import pe.com.galaxy.training.escritorio.bean.ubicacion.Departamento;
import pe.com.galaxy.training.escritorio.persistence.util.PersistenciaException;

import java.util.List;

public interface DepartamentoBD {

    List<Departamento> listar(Departamento obj)throws PersistenciaException;
    
    Departamento seleccionar(Departamento obj)throws PersistenciaException;
}
