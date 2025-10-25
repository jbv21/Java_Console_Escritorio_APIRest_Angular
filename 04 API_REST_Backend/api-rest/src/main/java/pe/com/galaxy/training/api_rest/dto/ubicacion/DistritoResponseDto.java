package pe.com.galaxy.training.api_rest.dto.ubicacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistritoResponseDto {
    private String id;
    private String nombre;
    private String idDepartamento;
    private String idProvincia;
}
