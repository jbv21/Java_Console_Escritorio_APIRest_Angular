package pe.com.galaxy.training.api_rest.services.ubicacion;

public class ProvinciaException extends RuntimeException {
    public ProvinciaException(String message) {
        super(message);
    }

    public ProvinciaException() {
        super();
    }

    public ProvinciaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProvinciaException(Throwable cause) {
        super(cause);
    }

    protected ProvinciaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
