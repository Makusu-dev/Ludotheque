package fr.eni.ludoteque.dal;


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
    @Transactional
    public void ajoutClient() {
        // AAA Arrange, Act, Assert
        //Arrange
        Client nouveauClient = new Client("bob","dupont","bob.email@mail.com");
        nouveauClient.setNo_telephone("0101010110");

        //Act
        Client newClient = clientsRepository.save(nouveauClient);

        assertNotNull(newClient);
        assertNotNull(newClient.getNoClient());
        assertEquals(nouveauClient.getNom(),newClient.getNom());

        //clientsRepository.flush();

        //Assert
        Optional<Client> searchClientOpt = clientsRepository.findById(newClient.getNoClient());
        assertTrue(searchClientOpt.isPresent());
        assertEquals(nouveauClient, searchClientOpt.get());

    }


}
