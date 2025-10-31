package fr.eni.ludoteque.bo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Jeu {
    @Id
    @UuidGenerator
    @EqualsAndHashCode.Exclude
    private UUID noJeu;

    @NonNull
    @Column(nullable=false)
    private String titre;

    @NonNull
    @Column(nullable=false)
    private String reference;

    @NonNull
    @Column(nullable=false)
    private Float tarifJour;

    private String description;
    private int ageMin;
    private int duree;

    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinTable(name="JeuGenre",
            joinColumns= {@JoinColumn(name="no_jeu")},
            inverseJoinColumns= {@JoinColumn(name="no_genre")}
    )
    private List<Genre> genres = new ArrayList<Genre>();

    public void addGenre(Genre genre){
        genres.add(genre);
    }









}
