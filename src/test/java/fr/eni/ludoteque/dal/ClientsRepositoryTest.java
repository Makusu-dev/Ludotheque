package fr.eni.ludoteque.dal;


import fr.eni.ludoteque.bo.Adresse;
import fr.eni.ludoteque.bo.Client;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientsRepositoryTest {

    @Autowired
    private ClientsRepository clientsRepository;


    @Test
    @DisplayName("Test d'ajout d'un client en BD - cas droit")
    public void ajoutClient() {
        // AAA Arrange, Act, Assert
        //Arrange
        Client client = new Client("bob","dupont","bob.email@mail.com");
        client.setNoTelephone("0101010110");

        //Act
        Client newClient = clientsRepository.save(client);

        assertNotNull(newClient);
        assertNotNull(newClient.getNoClient());
        assertEquals(client.getNom(),newClient.getNom());

        //Assert
        Optional<Client> searchClientOpt = clientsRepository.findById(newClient.getNoClient());
        assertTrue(searchClientOpt.isPresent());
        assertEquals(client, searchClientOpt.get());

    }

    @Test
    @DisplayName("Test de l'association d'une adresse")
    public void associationAdresseTest() {
        // Arrange
        Client client = new Client("edouard","jhabitequelquepart","ed.jqqp@mail.com");
        Adresse adresse = new Adresse("rue du test","99100","Java-sur-Meuse");

        //act
        client.setAdresse(adresse);
        Client newClient = clientsRepository.save(client);
        //On ne save pas l'adresse pour tester le persist cascade
        //test intermédiaire d'association de l'adresse
        assertNotNull(newClient.getAdresse());

        //assert
        Optional<Client> searchClientOpt = clientsRepository.findById(newClient.getNoClient());
        assertTrue(searchClientOpt.isPresent());
        assertEquals(adresse, searchClientOpt.get().getAdresse());

    }



}
