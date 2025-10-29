package fr.eni.ludoteque.bll;

import fr.eni.ludoteque.bo.Client;
import fr.eni.ludoteque.dto.ClientDto;

public interface ClientService {
    Client ajouterClient(ClientDto client);
}
