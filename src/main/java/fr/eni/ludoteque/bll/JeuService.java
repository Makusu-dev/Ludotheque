package fr.eni.ludoteque.bll;
import fr.eni.ludoteque.bo.Exemplaire;
import fr.eni.ludoteque.bo.Jeu;
import fr.eni.ludoteque.dto.JeuDto;

import java.util.UUID;


public interface JeuService {
    Jeu ajouterJeu(JeuDto Jeu);

    Jeu trouverJeuParNoJeu(UUID noJeu);

    int disponibiliteParJeu(UUID noJeu);

    Exemplaire ajouterExemplaire(UUID idJeu);
}
