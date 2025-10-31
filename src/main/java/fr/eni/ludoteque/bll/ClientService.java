package fr.eni.ludoteque.bll;

import fr.eni.ludoteque.bo.Adresse;
import fr.eni.ludoteque.bo.Client;
import fr.eni.ludoteque.dto.ClientDto;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    Client ajouterClient(ClientDto client);

    List<Client> trouverClientsParNomDebutant(String chaine);

    Client trouverPremierClientParNom(String nom);

    Client modifierClient(UUID noClient, ClientDto client);

    Adresse modifierAdresseClient(UUID noClient, Adresse adresse);

    void supprimerClient(UUID id);

    Client trouverClientParId(UUID id);
}
