package fr.eni.ludoteque.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Genre implements Serializable {
    @Serial
    private static final long serialVersionUID
            = 1L;
    @Id
    @UuidGenerator
    @EqualsAndHashCode.Exclude
    private UUID noGenre;

    @NonNull
    private String libelle;

}
