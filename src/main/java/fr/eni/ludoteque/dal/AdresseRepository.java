package fr.eni.ludoteque.dal;

import fr.eni.ludoteque.bo.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface AdresseRepository extends JpaRepository<Adresse, Integer>{

    Adresse findAdresseByNoAdresse(UUID noAdresse);
}
