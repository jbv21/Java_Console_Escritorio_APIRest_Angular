package pe.com.galaxy.training.api_rest.controller.escuela;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.galaxy.training.api_rest.dto.escuela.EscuelaConduccionRequestDTO;
import pe.com.galaxy.training.api_rest.dto.escuela.EscuelaConduccionResponseDTO;
import pe.com.galaxy.training.api_rest.services.escuela.EscuelaConduccionException;
import pe.com.galaxy.training.api_rest.services.escuela.EscuelaConduccionService;
import pe.com.galaxy.training.api_rest.util.ApiResponse;
import pe.com.galaxy.training.api_rest.util.RespuestaEnum;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/escuelas")
public class EscuelaConduccionController {

    @Autowired
    private EscuelaConduccionService escuelaConduccionService;


    @GetMapping("")
    public ResponseEntity<ApiResponse<?>> findAll(
            @RequestParam(value ="idDepartamento",defaultValue = "")  String idDepartamento,
            @RequestParam(value ="idProvincia",defaultValue = "")  String idProvincia,
            @RequestParam(value ="idDistrito",defaultValue = "")  String idDistrito,
            @RequestParam(value ="ruc",defaultValue = "")  String ruc
    ) throws EscuelaConduccionException {
        EscuelaConduccionRequestDTO obj = new EscuelaConduccionRequestDTO();
        if(idDepartamento!=null && !idDepartamento.isEmpty()){ obj.setIdDepartamento(idDepartamento); }
        if(idProvincia!=null && !idProvincia.isEmpty()){ obj.setIdProvincia(idProvincia); }
        if(idDistrito!=null && !idDistrito.isEmpty()){ obj.setIdDistrito(idDistrito); }
        if(ruc!=null && !ruc.isEmpty()){ obj.setRuc(ruc); }

        List<EscuelaConduccionResponseDTO> rs = escuelaConduccionService.findAll(obj);
        if(rs==null || rs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<Void>(RespuestaEnum.ERROR_LISTADO_SIN_DATOS));
        }
        return ResponseEntity.ok(
                new ApiResponse<>( RespuestaEnum.OK_LISTADO, rs )
        );
    }


    @GetMapping("/paging")
    public ResponseEntity<ApiResponse<?>> findAllCustomPaging(
            @RequestParam(value ="idDepartamento",defaultValue = "")  String idDepartamento,
            @RequestParam(value ="idProvincia",defaultValue = "")  String idProvincia,
            @RequestParam(value ="idDistrito",defaultValue = "")  String idDistrito,
            @RequestParam(value ="ruc",defaultValue = "")  String ruc,
            @RequestParam(value ="page",defaultValue = "0")  Integer page,
            @RequestParam(value ="size",defaultValue = "10") Integer size
    ) throws EscuelaConduccionException {

        Pageable pageable = PageRequest.of(page, size);
        EscuelaConduccionRequestDTO obj = new EscuelaConduccionRequestDTO();
        if(idDepartamento!=null && !idDepartamento.isEmpty()){ obj.setIdDepartamento(idDepartamento); }
        if(idProvincia!=null && !idProvincia.isEmpty()){ obj.setIdProvincia(idProvincia); }
        if(idDistrito!=null && !idDistrito.isEmpty()){ obj.setIdDistrito(idDistrito); }
        if(ruc!=null && !ruc.isEmpty()){ obj.setRuc(ruc); }

        Page<EscuelaConduccionResponseDTO> rs = escuelaConduccionService.findAllCustomPaging(pageable, obj);
        if(rs==null || rs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<Void>(RespuestaEnum.ERROR_LISTADO_SIN_DATOS));
        }
        return ResponseEntity.ok(
                new ApiResponse<>( RespuestaEnum.OK_LISTADO, rs )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> findById(
            @Valid
            @PathVariable("id")
            @Min(value = 1, message = "El valor debe ser al menos 1") // Opcional: para asegurar que sea > 0
            @Max(value = 1000, message = "El valor no debe exceder 1000")
            @Pattern(regexp = "\\d+", message = "El ID solo debe contener dígitos numéricos")
            @NotNull(message = "El ID no puede ser nulo")
            String id
    ) throws EscuelaConduccionException {
        EscuelaConduccionRequestDTO obj = new EscuelaConduccionRequestDTO();
        obj.setId(Integer.valueOf(id));
        Optional<EscuelaConduccionResponseDTO> rs = escuelaConduccionService.findById(obj);
        if(rs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<Void>(RespuestaEnum.ERROR_SELECCIONADO_SIN_DATO));
        }
        return ResponseEntity.ok(
                new ApiResponse<>( RespuestaEnum.OK_SELECCIONADO, rs)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> save(
            @Valid
            @RequestBody
            EscuelaConduccionRequestDTO obj
    ) throws EscuelaConduccionException {
        EscuelaConduccionResponseDTO rs = escuelaConduccionService.save(obj);
        if(rs==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<Void>(RespuestaEnum.ERROR_CREADO));
        }
        return ResponseEntity.ok(
                new ApiResponse<>( RespuestaEnum.OK_CREADO, rs)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(
            @Valid
            @PathVariable("id")
            @Min(value = 1, message = "El valor debe ser al menos 1") // Opcional: para asegurar que sea > 0
            @Max(value = 1000, message = "El valor no debe exceder 1000")
            @Pattern(regexp = "\\d+", message = "El ID solo debe contener dígitos numéricos")
            @NotNull(message = "El ID no puede ser nulo")
            String id,
            @Valid
            @RequestBody
            EscuelaConduccionRequestDTO obj
    ) throws EscuelaConduccionException {
        obj.setId(Integer.valueOf(id));
        EscuelaConduccionResponseDTO rs = escuelaConduccionService.update(obj);
        if(rs==null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ApiResponse<Void>(RespuestaEnum.ERROR_ACTUALIZADO));
        }
        return ResponseEntity.ok(
                new ApiResponse<>( RespuestaEnum.OK_ACTUALIZADO, rs)
        );
    }

    @PatchMapping("/{id}/{estado}")
    public ResponseEntity<ApiResponse<?>> updateEstado(
            @Valid
            @PathVariable("id")
            @Max(value = 1000, message = "El valor no debe exceder 1000")
            @Min(value = 1, message = "El valor debe ser al menos 1") // Opcional: para asegurar que sea > 0
            @Pattern(regexp = "\\d+", message = "El ID solo debe contener dígitos numéricos")
            @NotNull(message = "El ID no puede ser nulo")
            String id,
            @PathVariable("estado")
            @NotNull(message = "El Estado no puede ser nulo")
            String estado
    ) throws EscuelaConduccionException {
        EscuelaConduccionRequestDTO obj = new EscuelaConduccionRequestDTO();
        obj.setId(Integer.valueOf(id));
        obj.setEstado(estado);
        EscuelaConduccionResponseDTO rs = escuelaConduccionService.updateEstado(obj);
        if(rs==null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ApiResponse<Void>(RespuestaEnum.ERROR_ACTUALIZADO));
        }
        return ResponseEntity.ok(
                new ApiResponse<>( RespuestaEnum.OK_ACTUALIZADO, rs)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(
            @Valid
            @PathVariable("id")
            @Max(value = 1000, message = "El valor no debe exceder 1000")
            @Min(value = 1, message = "El valor debe ser al menos 1") // Opcional: para asegurar que sea > 0
            @Pattern(regexp = "\\d+", message = "El ID solo debe contener dígitos numéricos")
            @NotNull(message = "El ID no puede ser nulo")
            String id
    ) throws EscuelaConduccionException {
        EscuelaConduccionRequestDTO obj = new EscuelaConduccionRequestDTO();
        obj.setId(Integer.valueOf(id));
        escuelaConduccionService.delete(obj);
        return ResponseEntity.ok(
                new ApiResponse<>( RespuestaEnum.OK_ELIMINADO, true)
        );

    }
}
