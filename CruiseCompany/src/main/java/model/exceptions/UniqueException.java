package model.exceptions;

public class UniqueException extends RuntimeException {
    private String value;

    public UniqueException(Throwable cause) {
        super(cause);
    }

    public UniqueException(String message, String value) {
        super(message);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
