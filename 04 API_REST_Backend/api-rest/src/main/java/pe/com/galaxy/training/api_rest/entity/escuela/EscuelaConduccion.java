package pe.com.galaxy.training.api_rest.entity.escuela;

import jakarta.persistence.*;
import lombok.*;
import pe.com.galaxy.training.api_rest.entity.ubicacion.Ubigeo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="escuela_conduccion")
public class EscuelaConduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="RUC")
    private String ruc;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="DIRECCION")
    private String direccion;

    @Column(name="ESTADO")
    private String estado;

    @Column(name="TELEFONO")
    private String telefono;

    @Column(name="CORREO")
    private String correo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ID_DEP", referencedColumnName = "ID_DEP"),
            @JoinColumn(name = "ID_PRO", referencedColumnName = "ID_PRO"),
            @JoinColumn(name = "ID_DIS", referencedColumnName = "ID_DIS")
    })
    private Ubigeo ubigeo;

    @Transient
    private String idDepartamento;

    @Transient
    private String idProvincia;

    @Transient
    private String idDistrito;

    @Column(name="ACTIVO")
    private Integer activo;
}
