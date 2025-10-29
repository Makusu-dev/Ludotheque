package fr.eni.ludoteque.bll;

import fr.eni.ludoteque.bo.Adresse;
import fr.eni.ludoteque.bo.Client;
import fr.eni.ludoteque.dal.ClientsRepository;
import fr.eni.ludoteque.dto.ClientDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientsRepository clientsRepository;

    public ClientServiceImpl(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Override
    public Client ajouterClient(ClientDto client) {
        Client newClient = new Client();
        Adresse adresse = new Adresse();
        BeanUtils.copyProperties(client, newClient);
        BeanUtils.copyProperties(adresse, newClient);
        newClient.setAdresse(adresse);


        return clientsRepository.save(newClient);
    }
}
