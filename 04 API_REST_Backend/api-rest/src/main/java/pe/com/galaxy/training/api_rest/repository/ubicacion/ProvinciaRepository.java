package pe.com.galaxy.training.api_rest.repository.ubicacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.galaxy.training.api_rest.entity.ubicacion.Provincia;

import java.util.List;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, String> {

    @Query("SELECT p " +
            " FROM Provincia p " +
            " WHERE p.idDepartamento = :P_ID_DEP " +
            " GROUP BY " +
            "   p.id, p.nombre ")
    List<Provincia> findAll(@Param("P_ID_DEP") String idDepartamento);
}
