package pe.com.galaxy.training.api_rest.dto.ubicacion;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UbigeoIdRequestDto {


    @NotNull(message = "IdDepartamento no puede estar vacío")
    private String idDepartamento;

    @NotNull(message = "IdProvincia no puede estar vacío")
    private String idProvincia;

    @NotNull(message = "IdDistrito no puede estar vacío")
    private String idDistrito;
}
