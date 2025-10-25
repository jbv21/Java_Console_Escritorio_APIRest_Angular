package pe.com.galaxy.training.api_rest.services.seguridad;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.galaxy.training.api_rest.entity.seguridad.Usuario;
import pe.com.galaxy.training.api_rest.repository.seguridad.UsuarioRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> authorization(String usuario, String clave) {
        return usuarioRepository.authorization(usuario,clave);
    }
}
