package pe.com.galaxy.training.api_rest.dto.ubicacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UbigeResponseDto {

    private UbigeoResponseIdDto id;
    private String nombreDepartamento;
    private String nombreProvincia;
    private String nombreDistrito;

}


