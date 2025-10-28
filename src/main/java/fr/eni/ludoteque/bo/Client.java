package fr.eni.ludoteque.bo;

import lombok.*;

import java.util.UUID;

@Data @NoArgsConstructor @RequiredArgsConstructor
public class Client {

    // non obligatoire car avec le fonctionnement de jpa il faudra éventuellement construire des instances sans no_client
    private UUID noClient;
    @NonNull private String nom;
    @NonNull private String prenom;
    @NonNull private String email;
    private String no_telephone;


    @NonNull private Adresse adresse;

}
