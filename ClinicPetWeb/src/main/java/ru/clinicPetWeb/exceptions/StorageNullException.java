package ru.clinicPetWeb.exceptions;

public class StorageNullException extends Exception {

    public StorageNullException() {
        super();
    }

    public StorageNullException(String message) {
        super(message);
    }

    public StorageNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageNullException(Throwable cause) {
        super(cause);
    }
}
