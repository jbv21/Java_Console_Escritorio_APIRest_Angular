package pe.com.galaxy.training.api_rest.dto.escuela;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.galaxy.training.api_rest.dto.ubicacion.UbigeoRequestDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EscuelaConduccionRequestDTO {
    private Integer id;

    @NotNull(message = "RUC no puede estar vacío")
    @Size(min = 11, max = 11, message = "Debe ser 11 caracteres")
    private String ruc;

    @NotNull(message = "Nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "Dirección no puede estar vacío")
    private String direccion;

    @NotNull(message = "Estado no puede estar vacío")
    private String estado;

    @NotNull(message = "Teléfono no puede estar vacío")
    @Size(max = 20, message = "Máximo 20 caracteres")
    private String telefono;

    @NotNull(message = "Correo no puede estar vacio")
    @Email(message = "Formto de Correo no es válido")
    private String correo;

    @NotNull(message = "Ubigeo no puede estar vacio")
    private UbigeoRequestDto ubigeo;


    private String idDepartamento;
    private String idProvincia;
    private String idDistrito;

}
