package fr.eni.ludoteque.dal;

import fr.eni.ludoteque.bo.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, UUID> {
}
