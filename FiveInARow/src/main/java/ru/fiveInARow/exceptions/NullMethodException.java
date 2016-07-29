package ru.fiveInARow.exceptions;

public class NullMethodException extends GameLogicException {

    public NullMethodException() {
        super();
    }

    public NullMethodException(String message) {
        super(message);
    }

    public NullMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullMethodException(Throwable cause) {
        super(cause);
    }
}
