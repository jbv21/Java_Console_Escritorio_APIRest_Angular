package pe.com.galaxy.training.api_rest.util;

import lombok.Getter;

@Getter
public enum RespuestaEnum {
    // ---- OK ----
    OK_ACCESO(true,"ACCESO", "Acceso concedido correctamente"),
    OK_SELECCIONADO(true,"CONSULTA", "Consulta realizada con éxito"),
    OK_LISTADO(true,"LISTADO", "Listado obtenido correctamente"),
    OK_CREADO(true,"CREADO", "Registro creado exitosamente"),
    OK_ACTUALIZADO(true,"ACTUALIZADO", "Registro actualizado correctamente"),
    OK_ELIMINADO(true,"ELIMINADO", "Registro eliminado correctamente"),
    OK_PROCESO(true,"PROCESO", "Proceso ejecutado correctamente"),

    // ---- ERRORES ----
    ERROR_ACCESO(false,"ERROR", "Error en el acceso, credenciales inválidas o sin autorización"),
    ERROR_SELECCIONADO(false,"ERROR", "No se pudo consultar la información"),
    ERROR_SELECCIONADO_SIN_DATO(false,"ERROR", "No existe el dato para consultar"),
    ERROR_LISTADO(false,"ERROR", "Error al obtener el listado de registros"),
    ERROR_LISTADO_SIN_DATOS(false,"ERROR", "No existen datos para consultar"),
    ERROR_CREADO(false,"ERROR", "No se pudo crear el registro"),
    ERROR_ACTUALIZADO(false,"ERROR", "No se pudo actualizar el registro"),
    ERROR_ELIMINADO(false,"ERROR", "No se pudo eliminar el registro"),
    ERROR_PROCESO(false,"ERROR", "Ocurrió un error durante el proceso"),
    ERROR_PROCESO_SIN_DATO(false,"ERROR", "No existe el dato para procesar"),
    ERROR_PROCESO_DATO_DUPLICADO(false,"ERROR", "Ya Existe el dato para procesar"),

    ERROR_VALIDACION(false,"VALIDACIÓN", "Existen errores de validación en los datos proporcionados"),
    ERROR_INTERNO(false,"ERROR INTERNO", "Existe error interno en la aplicación");

    private final Boolean estado;
    private final String titulo;
    private final String mensaje;
    RespuestaEnum(Boolean estado, String titulo, String mensaje){
        this.estado = estado;
        this.titulo = titulo;
        this.mensaje = mensaje;
    }

}
