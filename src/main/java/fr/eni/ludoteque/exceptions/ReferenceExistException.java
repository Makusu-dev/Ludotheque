package fr.eni.ludoteque.exceptions;

public class ReferenceExistException extends RuntimeException {
    public ReferenceExistException() {
        super("Cette référence existe déjà");
    }
}
