package pe.com.galaxy.training.api_rest.dto.ubicacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UbigeoResponseIdDto {

    private String idDepartamento;
    private String idProvincia;
    private String idDistrito;
}
