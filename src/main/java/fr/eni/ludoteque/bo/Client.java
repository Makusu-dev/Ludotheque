package fr.eni.ludoteque.bo;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data @NoArgsConstructor @RequiredArgsConstructor
@Entity
@Table(name = "CLIENTS")
public class Client {

    // non obligatoire car avec le fonctionnement de jpa il faudra éventuellement construire des instances sans no_client
    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer noClient;

    @Column(nullable = false, length = 100)
    @NonNull private String nom;

    @Column(nullable = false,length = 100)
    @NonNull private String prenom;

    @Column(nullable = false,length = 150,unique = true)
    @NonNull private String email;

    @Column(length = 15)
    private String no_telephone;


    //@NonNull private Adresse adresse;

}
