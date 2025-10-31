package fr.eni.ludoteque.dal;
import fr.eni.ludoteque.bo.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface JeuRepository extends JpaRepository<Jeu, UUID> {
    Jeu findByReference(String refWelcome);
    Jeu findByNoJeu(UUID noJeu);

    @Query(nativeQuery = true, value="select tarif_jour from jeu where no_jeu = :noJeu")
    Float findTarifJour(@Param("noJeu") UUID noJeu);
}
