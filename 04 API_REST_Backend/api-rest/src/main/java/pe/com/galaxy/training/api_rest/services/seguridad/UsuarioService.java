package pe.com.galaxy.training.api_rest.services.seguridad;

import pe.com.galaxy.training.api_rest.entity.seguridad.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> authorization(String usuario, String clave);

}
