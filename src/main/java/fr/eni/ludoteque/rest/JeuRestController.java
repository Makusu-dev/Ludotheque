package fr.eni.ludoteque.rest;

import fr.eni.ludoteque.bll.JeuService;
import fr.eni.ludoteque.bo.Client;
import fr.eni.ludoteque.bo.Genre;
import fr.eni.ludoteque.bo.Jeu;
import fr.eni.ludoteque.dal.JeuRepository;
import fr.eni.ludoteque.dto.JeuDto;
import fr.eni.ludoteque.exceptions.DataNotFound;
import fr.eni.ludoteque.exceptions.ReferenceExistException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class JeuRestController {
    private final JeuService jeuService;

    public JeuRestController(JeuService jeuService, JeuRepository jeuRepository) {
        this.jeuService = jeuService;
    }


        @PostMapping("/jeu")
        public ResponseEntity<JsonApiResponse<Jeu>> ajouterJeu(@Valid @RequestBody JeuDto jeuDto, BindingResult result){
            //TODO: lister les erreurs pour chaque champ pour afficher l'intégralité des erreurs
            if(result.hasErrors()){
                return ResponseEntity.badRequest().body(new JsonApiResponse<>("400","Il y a une erreur dans les champs"));
            }
            Jeu jeu;
            try {
                jeu = jeuService.ajouterJeu(jeuDto);
            }catch (ReferenceExistException e){
                return ResponseEntity.badRequest().body(new JsonApiResponse<>("401","La référence existe déjà en base de données"));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(new JsonApiResponse<>("200","Le jeu a été créé",jeu));
        }

        //TODO:Faire l'ajout d'exemplaires


        @GetMapping("/jeu/{id}")
            public ResponseEntity<JsonApiResponse<JeuDto>> searchJeuById(@PathVariable UUID id){
            Jeu jeu = null;

            try {
                jeu=jeuService.trouverJeuParNoJeu(id);
            }catch(DataNotFound e){
                return ResponseEntity.badRequest().body(new JsonApiResponse<>("404","Aucun résultat"));
            }

            int nombreExemplaires = jeuService.disponibiliteParJeu(id);
            JeuDto jeuDto = new JeuDto(jeu.getTitre(),jeu.getReference(),jeu.getTarifJour(),jeu.getDescription(),jeu.getAgeMin(),jeu.getDuree(),jeu.getGenres().stream().map(Genre::getLibelle).collect(Collectors.toList()),nombreExemplaires);


            //ajouter calcul nombre d'exemplaires

            return ResponseEntity.ok(new JsonApiResponse<JeuDto>("200","Jeu trouvé",jeuDto));
        }

    @GetMapping("/jeu/{id}/disponibilité")
    public ResponseEntity<JsonApiResponse<Integer>> searchDisponibiliteById(@PathVariable UUID id){
        int nombreExemplaires = 0;

        try {
            Jeu jeu = jeuService.trouverJeuParNoJeu(id);
            nombreExemplaires = jeuService.disponibiliteParJeu(id);
        }catch(DataNotFound e){
            return ResponseEntity.badRequest().body(new JsonApiResponse<>("404","Aucun résultat"));
        }
        return ResponseEntity.ok(new JsonApiResponse<>("200","Jeu trouvé",nombreExemplaires));
    }



    }
