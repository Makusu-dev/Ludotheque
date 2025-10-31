package fr.eni.ludoteque.rest;

import fr.eni.ludoteque.bll.ClientService;
import fr.eni.ludoteque.bo.Adresse;
import fr.eni.ludoteque.bo.Client;
import fr.eni.ludoteque.dal.ClientsRepository;
import fr.eni.ludoteque.dto.ClientDto;
import fr.eni.ludoteque.exceptions.DataNotFound;
import fr.eni.ludoteque.exceptions.EmailClientAlreadyExistException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService, ClientsRepository clientsRepository) {
        this.clientService = clientService;
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id){
        clientService.supprimerClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/clients")
    public ResponseEntity<JsonApiResponse<Client>> ajouterClient(@Valid @RequestBody ClientDto clientDto,  BindingResult result) {

        //TODO: lister les erreurs pour chaque champ pour afficher l'intégralité des erreurs
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(new JsonApiResponse<Client>("400","Il y a une erreur dans les champs les champs"));
        }

        Client client = null;
        try {
            client=clientService.ajouterClient(clientDto);
        } catch (EmailClientAlreadyExistException e) {
            return ResponseEntity.badRequest().body(new JsonApiResponse<Client>("401","L'email existe déjà en base de données"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new JsonApiResponse<Client>("200","Le client a été créé",client));

    }

    @PatchMapping("/clients/{id}")
    public ResponseEntity<JsonApiResponse<Client>> modifierClient(@PathVariable UUID id,@Valid @RequestBody ClientDto clientdto, BindingResult result){


        Client client = null;
        try {
            client=clientService.modifierClient(id, clientdto);
        }catch (EmailClientAlreadyExistException e) {
            return ResponseEntity.badRequest().body(new JsonApiResponse<Client>("401","L'email existe déjà en base de données"));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new JsonApiResponse<Client>("200","Le client a été modifié",client));
    }

    @PatchMapping("/clients/{id}/adresse")
    public ResponseEntity<JsonApiResponse<Adresse>> modifierAdresseClient(@PathVariable UUID id, @RequestBody Adresse adresse){
        Adresse adresse1 = null;

        try {
            adresse1=clientService.modifierAdresseClient(id, adresse);
        }catch (EmailClientAlreadyExistException e) {
            return ResponseEntity.badRequest().body(new JsonApiResponse<Adresse>("401","L'email existe déjà en base de données"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new JsonApiResponse<Adresse>("200","L'adresse a été modifié",adresse1));
    }

    @GetMapping("/clients/search/{searchString}")
    public ResponseEntity<JsonApiResponse<List<Client>>> searchClient(@PathVariable String searchString){
        List<Client> listeClient=null;
        try{
            listeClient=clientService.trouverClientsParNomDebutant(searchString);
            Client firstResult=listeClient.get(0);
        }catch (IndexOutOfBoundsException e){
            return ResponseEntity.badRequest().body(new JsonApiResponse<>("404","Aucun résultat"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new JsonApiResponse<>("200","Voici vos résultats",listeClient));
    }

    @GetMapping("/clients/search/id/{id}")
    public ResponseEntity<JsonApiResponse<Client>> searchClientById(@PathVariable UUID id){
        Client client = null;
        try {
            client=clientService.trouverClientParId(id);
        } catch (DataNotFound e){
            return ResponseEntity.badRequest().body(new JsonApiResponse<Client>("404","Aucun client n'a été trouvé"));
        }
        return ResponseEntity.ok(new JsonApiResponse<Client>("200","Le client a été trouvé",client));
    }





}
