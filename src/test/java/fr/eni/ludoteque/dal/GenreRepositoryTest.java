package fr.eni.ludoteque.dal;

import fr.eni.ludoteque.bo.Client;
import fr.eni.ludoteque.bo.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("Test d'ajout d'un genre en BD - cas droit")
    public void ajoutGenre() {

        //arrange
        Genre genre = new Genre("TCG");

        //act
        Genre newGenre = genreRepository.save(genre);

        //assert
        assertNotNull(newGenre);
        Optional<Genre> searchGenreOpt = genreRepository.findById(newGenre.getNoGenre());
    }

}
