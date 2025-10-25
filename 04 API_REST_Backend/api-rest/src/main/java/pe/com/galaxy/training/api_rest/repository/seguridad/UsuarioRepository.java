package pe.com.galaxy.training.api_rest.repository.seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.galaxy.training.api_rest.entity.seguridad.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select e from Usuario e where e.usuario=:usuario and e.clave=:clave and e.activo=1")
    Optional<Usuario> authorization(@Param("usuario") String usuario, @Param("clave") String clave);
}
