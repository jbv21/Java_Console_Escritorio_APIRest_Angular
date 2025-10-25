package pe.com.galaxy.training.api_rest.repository.ubicacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.com.galaxy.training.api_rest.entity.ubicacion.Departamento;

import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, String> {

    @Query("SELECT d " +
            " FROM Departamento d " +
            " GROUP BY " +
            "   d.id, d.nombre ")
    List<Departamento> findAll();

}
