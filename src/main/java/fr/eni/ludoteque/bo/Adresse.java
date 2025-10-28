package fr.eni.ludoteque.bo;

import lombok.*;

@Data @AllArgsConstructor @Builder
public class Adresse {
    @EqualsAndHashCode.Exclude
    private String noAdresse;

    @NonNull private String rue;
    @NonNull private String code_postal;
    @NonNull private String ville;
}
