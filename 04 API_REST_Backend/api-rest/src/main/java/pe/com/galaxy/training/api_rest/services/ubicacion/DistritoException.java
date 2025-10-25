package pe.com.galaxy.training.api_rest.services.ubicacion;

public class DistritoException extends RuntimeException {
    public DistritoException(String message) {
        super(message);
    }

    public DistritoException() {
        super();
    }

    public DistritoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistritoException(Throwable cause) {
        super(cause);
    }

    protected DistritoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
