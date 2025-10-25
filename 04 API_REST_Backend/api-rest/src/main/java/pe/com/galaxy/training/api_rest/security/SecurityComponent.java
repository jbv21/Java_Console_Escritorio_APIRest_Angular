package pe.com.galaxy.training.api_rest.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.com.galaxy.training.api_rest.entity.seguridad.Usuario;
import pe.com.galaxy.training.api_rest.services.seguridad.UsuarioService;
import pe.com.galaxy.training.api_rest.util.Encrypt;

import java.util.Base64;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SecurityComponent {
    private final UsuarioService usuarioService;

    public Boolean authorization(String authorization) {
        System.out.println("Authorization => " + authorization);

        String data = authorization.substring(6);

        System.out.println("data => " + data);


        Base64.Decoder decoder = Base64.getDecoder();

        byte[] decodedBytes = decoder.decode(data);

        String dataDecode = new String(decodedBytes);

        System.out.println("dataDecode => " + dataDecode);

        String seg[] = dataDecode.split(":");

        if (seg.length!=2) {
            return false;
        }

        String usuarioEncrypt= Encrypt.encrypt(seg[1]);

        Optional<Usuario> optUsuario= usuarioService.authorization(seg[0], usuarioEncrypt);

        return !optUsuario.isEmpty();

    }
}
