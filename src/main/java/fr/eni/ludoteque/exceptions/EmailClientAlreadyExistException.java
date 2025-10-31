package fr.eni.ludoteque.exceptions;

import java.util.UUID;

public class EmailClientAlreadyExistException extends RuntimeException {
    public EmailClientAlreadyExistException() {
        super("cet email existe déjà");
    }
}
