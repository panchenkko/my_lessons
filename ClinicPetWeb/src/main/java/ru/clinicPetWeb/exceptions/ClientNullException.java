package ru.clinicPetWeb.exceptions;

public class ClientNullException extends Exception {

    public ClientNullException() {
        super();
    }

    public ClientNullException(String message) {
        super(message);
    }

    public ClientNullException(String message, Throwable cause) {
            super(message, cause);
        }

    public ClientNullException(Throwable cause) {
        super(cause);
    }
}
