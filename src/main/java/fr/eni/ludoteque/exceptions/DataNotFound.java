package fr.eni.ludoteque.exceptions;

import java.util.UUID;

public class DataNotFound extends RuntimeException {

    public DataNotFound(String type, UUID noClient) {
        super("Client " + noClient + " n'existe pas");
    }
}
