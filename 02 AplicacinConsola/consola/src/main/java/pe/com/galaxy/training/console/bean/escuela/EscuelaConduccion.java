package pe.com.galaxy.training.console.bean.escuela;

import pe.com.galaxy.training.console.bean.ubicacion.Departamento;
import pe.com.galaxy.training.console.bean.ubicacion.Distrito;
import pe.com.galaxy.training.console.bean.ubicacion.Provincia;

import java.util.Objects;

public class EscuelaConduccion {
    private Integer id;
    private String ruc;
    private String nombre;
    private String direccion;
    private String estado;
    private String telefono;
    private String correo;

    private String idDistrito;
    private String idProvincia;
    private String idDepartamento;

    private Distrito distrito;
    private Provincia provincia;
    private Departamento departamento;

    private Integer activo;

    public EscuelaConduccion() {
    }

    public EscuelaConduccion(Integer id, String ruc, String nombre, String direccion, String estado, String telefono, String correo, String idDistrito, String idProvincia, String idDepartamento, Distrito distrito, Provincia provincia, Departamento departamento, Integer activo) {
        this.id = id;
        this.ruc = ruc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estado = estado;
        this.telefono = telefono;
        this.correo = correo;
        this.idDistrito = idDistrito;
        this.idProvincia = idProvincia;
        this.idDepartamento = idDepartamento;
        this.distrito = distrito;
        this.provincia = provincia;
        this.departamento = departamento;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(String idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EscuelaConduccion that = (EscuelaConduccion) o;
        return id == that.id && Objects.equals(ruc, that.ruc) && Objects.equals(nombre, that.nombre) && Objects.equals(direccion, that.direccion) && Objects.equals(estado, that.estado) && Objects.equals(telefono, that.telefono) && Objects.equals(correo, that.correo) && Objects.equals(idDistrito, that.idDistrito) && Objects.equals(idProvincia, that.idProvincia) && Objects.equals(idDepartamento, that.idDepartamento) && Objects.equals(distrito, that.distrito) && Objects.equals(provincia, that.provincia) && Objects.equals(departamento, that.departamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ruc, nombre, direccion, estado, telefono, correo, idDistrito, idProvincia, idDepartamento, distrito, provincia, departamento);
    }

    @Override
    public String toString() {
        return  " id=" + id +
                "|  ruc='" + ruc + '\'' +
                "| nombre='" + nombre + '\'' +
                "| direccion='" + direccion + '\'' +
                "| estado='" + estado + '\'' +
                "| telefono='" + telefono + '\'' +
                "| correo='" + correo + '\'' +
                "| distrito='" + idDistrito + '\'' +
                "| provincia='" + idProvincia + '\'' +
                "| departamento='" + idDepartamento + '\'';
    }
}
