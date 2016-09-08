package ru.clinicPetWeb.exceptions;

public class CRUDException extends Exception {

    public CRUDException() {
        super();
    }

    public CRUDException(String message) {
        super(message);
    }

    public CRUDException(String message, Throwable cause) {
        super(message, cause);
    }

    public CRUDException(Throwable cause) {
        super(cause);
    }
}
