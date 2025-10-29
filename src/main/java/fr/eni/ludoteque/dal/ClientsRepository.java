package fr.eni.ludoteque.dal;

import fr.eni.ludoteque.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

//TODO: id en integer a changer dès que possible en string
public interface ClientsRepository extends JpaRepository<Client, UUID> {
}
