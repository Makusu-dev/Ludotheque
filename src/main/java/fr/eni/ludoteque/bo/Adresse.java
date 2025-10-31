package fr.eni.ludoteque.bo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data @NoArgsConstructor @RequiredArgsConstructor
@Entity
public class Adresse {
    @Id
    @UuidGenerator
    @EqualsAndHashCode.Exclude
    private UUID noAdresse;

    @NonNull
    @Column(nullable = false,length = 150)
    private String rue;

    @NonNull
    @Column(nullable = false,length = 6)
    private String codePostal;

    @NonNull
    @Column(nullable = false,length = 50)
    private String ville;

}
