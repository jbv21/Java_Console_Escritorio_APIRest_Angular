package pe.com.galaxy.training.api_rest.dto.ubicacion;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UbigeoRequestDto {

    @NotNull(message = "Id no puede estar vacío")
    private UbigeoIdRequestDto id;
    private String nombreDepartamento;
    private String nombreProvincia;
    private String nombreDistrito;

}


