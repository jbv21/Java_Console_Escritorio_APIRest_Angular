package pe.com.galaxy.training.api_rest.dto.escuela;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.galaxy.training.api_rest.entity.ubicacion.Ubigeo;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EscuelaConduccionResponseDTO {
    private Integer id;
    private String ruc;
    private String nombre;
    private String direccion;
    private String estado;
    private String telefono;
    private String correo;
    private Ubigeo ubigeo;
}
