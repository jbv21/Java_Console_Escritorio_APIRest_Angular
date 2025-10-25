package pe.com.galaxy.training.escritorio.bean.ubicacion;

import java.util.Objects;

public class Provincia {
    private String id;
    private String nombre;
    private Departamento departamento;

    public Provincia() {
    }

    public Provincia(String id, String nombre, Departamento departamento) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Provincia provincia = (Provincia) o;
        return Objects.equals(id, provincia.id) && Objects.equals(nombre, provincia.nombre) && Objects.equals(departamento, provincia.departamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, departamento);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
