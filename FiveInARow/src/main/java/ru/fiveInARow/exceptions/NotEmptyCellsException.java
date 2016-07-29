package ru.fiveInARow.exceptions;

public class NotEmptyCellsException extends GameLogicException {

    public NotEmptyCellsException() {
        super();
    }

    public NotEmptyCellsException(String message) {
        super(message);
    }

    public NotEmptyCellsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEmptyCellsException(Throwable cause) {
        super(cause);
    }
}
