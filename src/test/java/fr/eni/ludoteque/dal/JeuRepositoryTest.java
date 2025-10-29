package fr.eni.ludoteque.dal;

import fr.eni.ludoteque.bo.Exemplaire;
import fr.eni.ludoteque.bo.Genre;
import fr.eni.ludoteque.bo.Jeu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JeuRepositoryTest {


    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private JeuRepository jeuRepository;
    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Test
    @DisplayName("Test d'ajout d'un jeu avec plusieurs genres")
    public void ajoutGenre() {

        //arrange
        Jeu jeu = new Jeu("Pokemon TCG","pkmTCG",10.0f);
        Genre genre = new Genre("TCG");
        Genre genre2 = new Genre("Jeu a licence");
        jeu.addGenre(genre);
        jeu.addGenre(genre2);

        //act
        Jeu newJeu = jeuRepository.save(jeu);

        //Assert
        assertNotNull(newJeu);

        assertEquals(2, newJeu.getGenres().size());
        assertEquals(genre, newJeu.getGenres().get(0));
        assertEquals(genre2, newJeu.getGenres().get(1));
        Optional<Genre> searchGenreOpt = genreRepository.findById(newJeu.getGenres().get(0).getNoGenre());
        Optional<Genre> searchGenreOpt2 = genreRepository.findById(newJeu.getGenres().get(1).getNoGenre());


    }

    @Test
    @DisplayName("Test d'ajout d'un jeu avec plusieurs genres")
    public void ajoutExemplaires() {


        //arrange
        Jeu jeu = new Jeu("Pokemon TCG","pkmTCG",10.0f);

        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setCodeBarre(01445514);
        exemplaire.setJeu(jeu);


        //act
        Jeu newJeu = jeuRepository.save(jeu);
        Exemplaire newExemplaire = exemplaireRepository.save(exemplaire);


        //Assert
        assertNotNull(exemplaire);

        //assertEquals(jeu, newJeu.get().get(0));

        Optional<Exemplaire> searchExemplaireOpt = exemplaireRepository.findById(newExemplaire.getNoExemplaire());
        Optional<Jeu> searchJeuOpt = jeuRepository.findById(newJeu.getNoJeu());
        assertTrue(searchExemplaireOpt.isPresent());
        assertTrue(searchJeuOpt.isPresent());
        assertEquals(searchExemplaireOpt.get().getJeu().getNoJeu(), searchJeuOpt.get().getNoJeu());




    }

}
