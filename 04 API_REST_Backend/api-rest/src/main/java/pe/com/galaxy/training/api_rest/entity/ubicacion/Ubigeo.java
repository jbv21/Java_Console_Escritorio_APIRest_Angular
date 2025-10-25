package pe.com.galaxy.training.api_rest.entity.ubicacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "ubigeo")
public class Ubigeo {

    @EmbeddedId
    private UbigeoId id;

    @Column(name = "NOMBRE_DEP")
    private String nombreDepartamento;

    @Column(name = "NOMBRE_PRO")
    private String nombreProvincia;

    @Column(name = "NOMBRE_DIS")
    private String nombreDistrito;

}

