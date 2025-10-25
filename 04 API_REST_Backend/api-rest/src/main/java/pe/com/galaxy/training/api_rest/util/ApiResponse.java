package pe.com.galaxy.training.api_rest.util;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class ApiResponse<T>  {

    private static final Logger logger = LoggerFactory.getLogger(ApiResponse.class);

    private boolean estado;
    private String titulo;
    private String mensaje;
    private T resultado;

    public ApiResponse(){}

    public ApiResponse(RespuestaEnum respuestaEnum) {
        this.estado = respuestaEnum.getEstado();
        this.titulo = respuestaEnum.getTitulo();
        this.mensaje = respuestaEnum.getMensaje();
        this.resultado = null;
    }

    public ApiResponse(RespuestaEnum respuestaEnum, T resultado) {
        this.estado = respuestaEnum.getEstado();
        this.titulo = respuestaEnum.getTitulo();
        this.mensaje = respuestaEnum.getMensaje();
        this.resultado = resultado;
    }

    public ApiResponse(RespuestaEnum respuestaEnum, T resultado, Exception e) {
        this.estado = respuestaEnum.getEstado();
        this.titulo = respuestaEnum.getTitulo();
        this.mensaje = respuestaEnum.getMensaje();
        this.resultado = resultado;
        logger.error("{} : {}",e.getMessage(),e.getLocalizedMessage(),e);
    }
}

