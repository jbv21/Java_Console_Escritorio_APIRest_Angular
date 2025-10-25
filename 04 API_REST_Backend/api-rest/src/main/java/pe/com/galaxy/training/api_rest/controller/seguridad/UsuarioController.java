package pe.com.galaxy.training.api_rest.controller.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.galaxy.training.api_rest.dto.seguridad.UsuarioRequestDto;
import pe.com.galaxy.training.api_rest.entity.seguridad.Usuario;
import pe.com.galaxy.training.api_rest.services.seguridad.UsuarioService;
import pe.com.galaxy.training.api_rest.util.ApiResponse;
import pe.com.galaxy.training.api_rest.util.RespuestaEnum;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/seguridad")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody UsuarioRequestDto obj){

        Optional<Usuario> rs = usuarioService.authorization(obj.getUsuario(), obj.getClave());
        if(rs.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ApiResponse<Void>(RespuestaEnum.ERROR_LISTADO_SIN_DATOS));
        }
        return ResponseEntity.ok(
                new ApiResponse<>( RespuestaEnum.OK_LISTADO, rs )
        );
    }


}
