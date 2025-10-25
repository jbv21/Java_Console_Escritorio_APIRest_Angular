package pe.com.galaxy.training.api_rest.entity.ubicacion;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UbigeoId implements Serializable {

    @Column(name = "ID_DEP")
    private String idDepartamento;

    @Column(name = "ID_PRO")
    private String idProvincia;

    @Column(name = "ID_DIS")
    private String idDistrito;
}
