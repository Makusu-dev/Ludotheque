package fr.eni.ludoteque.dal;

import fr.eni.ludoteque.bo.Exemplaire;
import fr.eni.ludoteque.bo.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, UUID> {
    Exemplaire findExemplaireByCodeBarre(String codeBarre);

    List<Exemplaire> findExemplairesByJeu(Jeu jeu);

    @Query(value="select * from Exemplaire e JOIN Jeu j ON e.no_jeu=j.no_jeu JOIN locations l ON e.no_exemplaire = l.no_exemplaire WHERE l.date_retour ISNULL AND e.louable = true AND j.no_jeu = 1? ", nativeQuery=true)
    List<Exemplaire> findExemplairesDisponiblesByJeu(UUID id);


}
