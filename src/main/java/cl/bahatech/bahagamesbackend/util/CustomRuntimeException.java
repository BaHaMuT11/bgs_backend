package cl.bahatech.bahagamesbackend.util;

public class CustomRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -5701697412066827261L;

    private final String message;

    public CustomRuntimeException(String message) {
        super(message);
        this.message = message;
    }

    public CustomRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

    public CustomRuntimeException(Throwable throwable) {
        this("Error inesperado", throwable);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
