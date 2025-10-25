package pe.com.galaxy.training.console.bean.ubicacion;

import java.util.Objects;

public class Distrito {
    private String id;
    private String nombre;
    private Provincia provincia;

    public Distrito() {
    }

    public Distrito(String id, String nombre, Provincia provincia) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Distrito distrito = (Distrito) o;
        return Objects.equals(id, distrito.id) && Objects.equals(nombre, distrito.nombre) && Objects.equals(provincia, distrito.provincia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, provincia);
    }

    @Override
    public String toString() {
        return  "id='" + id + '\'' +
                " | nombre='" + nombre + '\'';
    }
}
