package fr.eni.ludoteque.dal;

import fr.eni.ludoteque.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientsRepository extends JpaRepository<Client, Integer> {
}
