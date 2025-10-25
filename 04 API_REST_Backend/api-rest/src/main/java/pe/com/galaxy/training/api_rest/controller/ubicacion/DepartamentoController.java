package pe.com.galaxy.training.api_rest.controller.ubicacion;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.galaxy.training.api_rest.dto.ubicacion.DepartamentoResponseDto;
import pe.com.galaxy.training.api_rest.services.ubicacion.DepartamentoException;
import pe.com.galaxy.training.api_rest.services.ubicacion.DepartamentoService;
import pe.com.galaxy.training.api_rest.util.ApiResponse;
import pe.com.galaxy.training.api_rest.util.RespuestaEnum;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ubicacion/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping()
    public ResponseEntity<ApiResponse<?>> findAll() throws DepartamentoException {
        List<DepartamentoResponseDto> rs = departamentoService.findAll();

        if(rs==null || rs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<Void>(RespuestaEnum.ERROR_LISTADO_SIN_DATOS));
        }
        return ResponseEntity.ok(
                new ApiResponse<>( RespuestaEnum.OK_LISTADO, rs )
        );
    }
}
