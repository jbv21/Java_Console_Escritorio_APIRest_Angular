package pe.com.galaxy.training.api_rest.repository.ubicacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.galaxy.training.api_rest.entity.ubicacion.Distrito;
import java.util.List;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, String> {
    @Query("SELECT d " +
            " FROM Distrito d " +
            " WHERE d.idDepartamento = :P_ID_DEP " +
            "   AND d.idProvincia = :P_ID_PRO " +
            " GROUP BY " +
            "   d.id, d.nombre ")
    List<Distrito> findAll(@Param("P_ID_DEP") String idDepartamento, @Param("P_ID_PRO") String idProvincia);
}
