package fr.eni.ludoteque.bll;

import fr.eni.ludoteque.bo.Adresse;
import fr.eni.ludoteque.bo.Client;
import fr.eni.ludoteque.dal.ClientsRepository;
import fr.eni.ludoteque.dto.ClientDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientsRepository clientsRepository;


    @Test
    @DisplayName("test Ajouter Client Et Adresse Cas Positif")
    public void testAjouterClientEtAdresseCasPositif(){
     //arrange
     ClientDto clientDto = new ClientDto("n1",
             "p1",
             "n1.p1@mail.fr",
             "01454121",
             "rue1",
             "44400",
             "Reze");
     Client fauxClient = new Client();
     BeanUtils.copyProperties(clientDto,fauxClient);
     fauxClient.setAdresse(new Adresse());
     BeanUtils.copyProperties(clientDto,fauxClient.getAdresse());
     fauxClient.setNoClient(UUID.randomUUID());
     fauxClient.getAdresse().setNoAdresse(UUID.randomUUID());
     when(clientsRepository.save(any(Client.class))).thenReturn(fauxClient);
     //act
     Client newClient = clientService.ajouterClient(clientDto);
     //assert
     assertNotNull(newClient);
     assertNotNull(newClient.getNoClient());
     assertNotNull(newClient.getAdresse().getNoAdresse());
    }


    @Test
    @DisplayName("Recherche de clients dont le nom commence par une chaîne de caractères")
    public void testRechercheClients(){
        //Arrange
        ClientDto clientDto1 = new ClientDto("Dupond","p1","n1.p1@mail.fr",
                "01454121","rue1","44400","Reze");
        ClientDto clientDto2 = new ClientDto("Dupont","p1","n2.p2@mail.fr",
                "01454121","rue1","44400","Reze");
        ClientDto clientDto3 = new ClientDto("Martin","p1","n3.p3@mail.fr",
                "01454121","rue1","44400","Reze");
        Client fauxClient1 = new Client();Client fauxClient2 = new Client();Client fauxClient3 = new Client();
        BeanUtils.copyProperties(clientDto1,fauxClient1);BeanUtils.copyProperties(clientDto2,fauxClient2);BeanUtils.copyProperties(clientDto3,fauxClient3);
        fauxClient1.setAdresse(new Adresse());fauxClient2.setAdresse(new Adresse());fauxClient3.setAdresse(new Adresse());
        BeanUtils.copyProperties(clientDto1,fauxClient1.getAdresse());BeanUtils.copyProperties(clientDto2,fauxClient2.getAdresse());BeanUtils.copyProperties(clientDto3,fauxClient3.getAdresse());
        fauxClient1.setNoClient(UUID.randomUUID());fauxClient2.setNoClient(UUID.randomUUID());fauxClient3.setNoClient(UUID.randomUUID());
        fauxClient1.getAdresse().setNoAdresse(UUID.randomUUID());;fauxClient2.getAdresse().setNoAdresse(UUID.randomUUID());fauxClient3.getAdresse().setNoAdresse(UUID.randomUUID());
        //when(clientsRepository.save(any(Client.class))).thenReturn(fauxClient1,fauxClient2,fauxClient3);

        System.out.println(clientDto1.toString());
        System.out.println(clientDto2.toString());
        System.out.println(clientDto3.toString());


        //Act
        Client newClient1 = clientService.ajouterClient(clientDto1);
        Client newClient2 = clientService.ajouterClient(clientDto2);
        Client newClient3 = clientService.ajouterClient(clientDto3);

        List<Client> lesDupont=clientService.trouverClientsParNomDebutant("Dup");
        Client dupont = clientService.trouverPremierClientParNom("Dupont");
        //Assert

        assertNotNull(newClient1);assertNotNull(newClient2);assertNotNull(newClient3);
        assertEquals("Dupont",dupont.getNom());

        System.out.println(lesDupont);

        assertNotNull(lesDupont);
        assertEquals(2,lesDupont.size());

    }

    @Test
    @DisplayName("test de modification d'un client")
    public void testModificationClient(){
        //Arrange
        Client dupontInitial = clientService.trouverPremierClientParNom("Dupont");
        //act
        String nouveauMail = "albert.dupont@gmail.com";
        dupontInitial.setEmail(nouveauMail);

        System.out.println("dupontInitial.getEmail()"+dupontInitial.getEmail());

        ClientDto clientDto = new ClientDto(dupontInitial.getNom(), dupontInitial.getPrenom(),dupontInitial.getEmail(),dupontInitial.getNoTelephone(),dupontInitial.getAdresse().getRue(),dupontInitial.getAdresse().getCodePostal(),dupontInitial.getAdresse().getVille());


        System.out.println(clientDto);

        Client nouveauDupont=clientService.modifierClient(dupontInitial.getNoClient(),clientDto);

        //assert

        assertEquals(nouveauMail,nouveauDupont.getEmail());


    }

    @Test
    @DisplayName("test de modification d'une adresse client")
    public void testModificationAdresseClient(){
        //Arrange
        Client dupontInitial = clientsRepository.findFirstByNom("Dupont");
        //act
        Adresse nouvelleAdresse = new Adresse("rue de la soif","35000","Rennes");
        dupontInitial.setAdresse(nouvelleAdresse);

        assertNotNull(dupontInitial.getAdresse());

        clientsRepository.save(dupontInitial);

        //assert
        Client nouveauDupont=clientsRepository.findClientByNoClient(dupontInitial.getNoClient());
        assertEquals("35000",nouveauDupont.getAdresse().getCodePostal());
    }



}
