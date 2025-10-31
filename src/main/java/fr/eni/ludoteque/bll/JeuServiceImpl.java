package fr.eni.ludoteque.bll;

import fr.eni.ludoteque.bo.Exemplaire;
import fr.eni.ludoteque.bo.Genre;
import fr.eni.ludoteque.bo.Jeu;
import fr.eni.ludoteque.dal.ExemplaireRepository;
import fr.eni.ludoteque.dal.JeuRepository;
import fr.eni.ludoteque.dto.JeuDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class JeuServiceImpl implements JeuService {
    private final JeuService jeuService;
    private JeuRepository jeuRepository;
    private ExemplaireRepository exemplaireRepository;

    public JeuServiceImpl(JeuRepository jeuRepository, JeuService jeuService){
        this.jeuRepository = jeuRepository;
        this.jeuService = jeuService;
    }


    @Override
    public Jeu ajouterJeu(JeuDto jeuDto) {
        Jeu newJeu = new Jeu();
        Genre genre = new Genre();
        BeanUtils.copyProperties(jeuDto, newJeu);
        BeanUtils.copyProperties(jeuDto,genre);
        newJeu.addGenre(genre);
        return jeuRepository.save(newJeu);
    }

    @Override
    public Jeu trouverJeuParNoJeu(UUID noJeu) {
        return jeuRepository.findByNoJeu(noJeu);
    }

    @Override
    public int disponibiliteParJeu(UUID noJeu){
        return exemplaireRepository.findExemplairesDisponiblesByJeu(noJeu).size();
    }

    @Override
    public Exemplaire ajouterExemplaire(UUID idJeu){
        Exemplaire newExemplaire = new Exemplaire();
        int codeBarre = (int)Math.floor(Math.random()*99999999);
        newExemplaire.setCodeBarre(String.valueOf(codeBarre));
        Jeu jeu = jeuService.trouverJeuParNoJeu(idJeu);
        newExemplaire.setJeu(jeu);
        return exemplaireRepository.save(newExemplaire);
    }

}
