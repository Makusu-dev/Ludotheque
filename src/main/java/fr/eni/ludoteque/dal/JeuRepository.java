package fr.eni.ludoteque.dal;
import fr.eni.ludoteque.bo.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JeuRepository extends JpaRepository<Jeu, UUID> {
}
