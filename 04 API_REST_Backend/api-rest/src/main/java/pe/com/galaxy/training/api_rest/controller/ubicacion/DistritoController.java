package pe.com.galaxy.training.api_rest.controller.ubicacion;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.galaxy.training.api_rest.dto.ubicacion.DistritoRequestDto;
import pe.com.galaxy.training.api_rest.dto.ubicacion.DistritoResponseDto;
import pe.com.galaxy.training.api_rest.services.ubicacion.DistritoException;
import pe.com.galaxy.training.api_rest.services.ubicacion.DistritoService;
import pe.com.galaxy.training.api_rest.util.ApiResponse;
import pe.com.galaxy.training.api_rest.util.RespuestaEnum;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ubicacion/distritos")
public class DistritoController {

    @Autowired
    private DistritoService distritoService;

    @GetMapping("/{idDepartamento}/{idProvincia}")
    public ResponseEntity<ApiResponse<?>> findAll (
            @Valid
            @PathVariable("idDepartamento")
            @NotNull(message = "El ID DE DEPARTAMENTO es obligatorio")
            @Pattern(regexp = "\\d{2}", message = "El ID debe tener exactamente 2 dígitos numéricos")
            String idDepartamento,
            @Valid
            @PathVariable("idProvincia")
            @NotNull(message = "El ID DE PROVINCIA es obligatorio")
            @Pattern(regexp = "\\d{2}", message = "El ID debe tener exactamente 2 dígitos numéricos")
            String idProvincia
    ) throws DistritoException {
        DistritoRequestDto obj = new DistritoRequestDto();
        obj.setIdDepartamento(idDepartamento);
        obj.setIdProvincia(idProvincia);
        List<DistritoResponseDto> rs = distritoService.findAll(obj);
        if(rs==null || rs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<Void>(RespuestaEnum.ERROR_LISTADO_SIN_DATOS));
        }
        return ResponseEntity.ok(
                new ApiResponse<>( RespuestaEnum.OK_LISTADO, rs )
        );
    }
}
