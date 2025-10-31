package fr.eni.ludoteque.bll;

import fr.eni.ludoteque.bo.Adresse;
import fr.eni.ludoteque.bo.Client;
import fr.eni.ludoteque.dal.AdresseRepository;
import fr.eni.ludoteque.dal.ClientsRepository;
import fr.eni.ludoteque.dto.ClientDto;
import fr.eni.ludoteque.exceptions.DataNotFound;
import fr.eni.ludoteque.exceptions.EmailClientAlreadyExistException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientsRepository clientsRepository;
    private AdresseRepository adresseRepository;

    public ClientServiceImpl(ClientsRepository clientsRepository, AdresseRepository adresseRepository) {
        this.clientsRepository = clientsRepository;
        this.adresseRepository = adresseRepository;
    }

    @Override
    public Client ajouterClient(ClientDto clientDto) {
        Client client = new Client();
        Adresse adresse = new Adresse();
        BeanUtils.copyProperties(clientDto, client);
        BeanUtils.copyProperties(clientDto, adresse);
        client.setAdresse(adresse);

        Client newClient = null;

        try {
            newClient=clientsRepository.save(client);
        }catch(DataIntegrityViolationException exc){
            throw new EmailClientAlreadyExistException();
        }

        return newClient;
    }

    @Override
    public Client trouverPremierClientParNom(String nom) {
        return clientsRepository.findFirstByNom(nom);
    }

    @Override
    public Client modifierClient(UUID noClient,ClientDto clientDto) {
        Client newClient = new Client();
        newClient.setNoClient(noClient);
        newClient.setAdresse(new Adresse());
        BeanUtils.copyProperties(clientDto,newClient);
        BeanUtils.copyProperties(clientDto, newClient.getAdresse());
        Client clientBD = null;
        try {
            clientBD = clientsRepository.save(newClient);
        } catch (OptimisticLockingFailureException e) {
            throw new DataNotFound("Client", noClient);
        }
        return clientBD;
    }


    @Override
    public Adresse modifierAdresseClient(UUID noClient, Adresse adresse){
        UUID idAdresse = clientsRepository.findClientByNoClient(noClient).getAdresse().getNoAdresse();
        adresse.setNoAdresse(idAdresse);
        return  adresseRepository.save(adresse);
    }

    @Override
    public void supprimerClient(UUID id) {

        clientsRepository.deleteById(id);
    }

    @Override
    public Client trouverClientParId(UUID id) {

        Client client = clientsRepository.findClientByNoClient(id);
        if (client == null) {
            throw new DataNotFound("Client", id);
        }
        else  {
            return  clientsRepository.save(client);
        }
    }

    @Override
    public List<Client> trouverClientsParNomDebutant(String chaine) {

        return clientsRepository.findClientByNomIsStartingWith(chaine);
    }





}
