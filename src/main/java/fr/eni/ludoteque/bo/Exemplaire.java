package fr.eni.ludoteque.bo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Exemplaire {

    @Id
    @UuidGenerator
    @EqualsAndHashCode.Exclude
    private UUID noExemplaire;

    @Column(nullable = false,length = 20, unique = true)
    private String codeBarre;

    @NonNull
    @Column(nullable = false)
    private Boolean louable = true;

    @ManyToOne(cascade = {
            CascadeType.MERGE})
    @JoinColumn(name="no_jeu")
    @NonNull
    private Jeu jeu;

}
