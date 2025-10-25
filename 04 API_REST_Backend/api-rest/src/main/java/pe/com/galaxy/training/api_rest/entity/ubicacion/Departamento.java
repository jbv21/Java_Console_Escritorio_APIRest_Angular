package pe.com.galaxy.training.api_rest.entity.ubicacion;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ubigeo")
public class Departamento {

    @Id
    @Column(name="ID_DEP")
    private String id;

    @Column(name="NOMBRE_DEP")
    private String nombre;

}
