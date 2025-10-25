package pe.com.galaxy.training.api_rest.services.escuela;

public class EscuelaConduccionException extends Exception {
    public EscuelaConduccionException() {
        super();
    }

    public EscuelaConduccionException(String message) {
        super(message);
    }

    public EscuelaConduccionException(String message, Throwable cause) {
        super(message, cause);
    }

    public EscuelaConduccionException(Throwable cause) {
        super(cause);
    }

    protected EscuelaConduccionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
