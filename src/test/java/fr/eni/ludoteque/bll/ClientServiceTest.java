package fr.eni.ludoteque.bll;

import fr.eni.ludoteque.bo.Client;
import fr.eni.ludoteque.dal.ClientsRepository;
import fr.eni.ludoteque.dto.ClientDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

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
     ClientDto clientDto = new ClientDto("n1","p1", "n1.p1@mail.fr","01454121","rue1","44400","Reze");
     Client fauxClient = new Client();
     BeanUtils.copyProperties(clientDto,fauxClient);
     BeanUtils.copyProperties(fauxClient,clientsRepository);
     fauxClient.setNoClient(UUID.randomUUID());
     fauxClient.getAdresse().setNoAdresse(UUID.randomUUID());
     when(clientsRepository.save(any(Client.class))).thenReturn(fauxClient);


     //act
     Client newClient = clientService.ajouterClient(clientDto);

     //assert
     assertNotNull(newClient);



 }

}
