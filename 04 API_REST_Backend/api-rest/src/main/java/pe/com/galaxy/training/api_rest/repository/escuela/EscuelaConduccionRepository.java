package pe.com.galaxy.training.api_rest.repository.escuela;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.galaxy.training.api_rest.entity.escuela.EscuelaConduccion;

import java.util.List;

@Repository
public interface EscuelaConduccionRepository extends JpaRepository<EscuelaConduccion, Integer> {


    @Query("    SELECT e \n" +
            "    FROM EscuelaConduccion e \n" +
            "    JOIN FETCH e.ubigeo u \n" +
            "    WHERE e.activo = 1 \n" +
            "      AND (:P_ID_DEP IS NULL OR u.id.idDepartamento = :P_ID_DEP) \n" +
            "      AND (:P_ID_PRO IS NULL OR u.id.idProvincia = :P_ID_PRO) \n" +
            "      AND (:P_ID_DIS IS NULL OR u.id.idDistrito = :P_ID_DIS) \n" +
            "      AND (:P_RUC IS NULL OR e.ruc = :P_RUC)")
    List<EscuelaConduccion> findAllCustom(
            @Param("P_ID_DEP") String idDepartamento,
            @Param("P_ID_PRO") String idProvincia,
            @Param("P_ID_DIS") String idDistrito,
            @Param("P_RUC") String ruc);

    @Query("    SELECT e \n" +
            "    FROM EscuelaConduccion e \n" +
            "    JOIN FETCH e.ubigeo u \n" +
            "    WHERE e.activo = 1 \n" +
            "      AND (:P_ID_DEP IS NULL OR u.id.idDepartamento = :P_ID_DEP) \n" +
            "      AND (:P_ID_PRO IS NULL OR u.id.idProvincia = :P_ID_PRO) \n" +
            "      AND (:P_ID_DIS IS NULL OR u.id.idDistrito = :P_ID_DIS) \n" +
            "      AND (:P_RUC IS NULL OR e.ruc = :P_RUC)")
    Page<EscuelaConduccion> findAllCustomPaging(
            Pageable pageable,
            @Param("P_ID_DEP") String idDepartamento,
            @Param("P_ID_PRO") String idProvincia,
            @Param("P_ID_DIS") String idDistrito,
            @Param("P_RUC") String ruc);


}
