package pe.com.galaxy.training.api_rest.services.ubicacion;

public class DepartamentoException extends RuntimeException {
    public DepartamentoException(String message) {
        super(message);
    }

    public DepartamentoException() {
        super();
    }

    public DepartamentoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartamentoException(Throwable cause) {
        super(cause);
    }

    protected DepartamentoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
