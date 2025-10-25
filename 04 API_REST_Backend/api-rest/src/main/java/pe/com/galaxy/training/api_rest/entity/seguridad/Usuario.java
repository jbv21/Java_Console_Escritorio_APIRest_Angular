package pe.com.galaxy.training.api_rest.entity.seguridad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Usuario")
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="USUARIO")
    private String usuario;

    @Column(name="CLAVE")
    private String clave;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="ACTIVO")
    private Integer activo;
}
