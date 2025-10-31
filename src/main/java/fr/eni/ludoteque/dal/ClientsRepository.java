package fr.eni.ludoteque.dal;

import fr.eni.ludoteque.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;


public interface ClientsRepository extends JpaRepository<Client, UUID> {

    List<Client> findClientByNomIsStartingWith(String string);

    List<Client> findByNom(String nom);

    Client findFirstByNom(String nom);

    Client findClientByNoClient(UUID uuid);


    Client findByNoTelephone(String number);
}
