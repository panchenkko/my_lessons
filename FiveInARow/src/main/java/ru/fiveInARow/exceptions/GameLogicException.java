package ru.fiveInARow.exceptions;

public class GameLogicException extends Exception {

    public GameLogicException() {
        super();
    }

    public GameLogicException(String message) {
        super(message);
    }

    public GameLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameLogicException(Throwable cause) {
        super(cause);
    }
}
