package fr.eni.ludoteque.dal;
import fr.eni.ludoteque.bo.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FactureRepository extends JpaRepository<Facture, UUID>{

}
