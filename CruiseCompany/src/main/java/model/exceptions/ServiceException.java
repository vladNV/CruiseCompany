package model.exceptions;

public class ServiceException extends Exception {
    private String exceptionCause;
    public ServiceException(String reason) {
        super(reason);
        this.exceptionCause = reason;
    }

    public ServiceException(String message, Throwable cause) {
        initCause(cause);
        this.exceptionCause = message;
    }

    public String getExceptionCause() {
        return exceptionCause;
    }
}
