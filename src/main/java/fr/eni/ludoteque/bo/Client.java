package fr.eni.ludoteque.bo;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data @NoArgsConstructor @RequiredArgsConstructor
@Entity
@Table(name = "CLIENTS")
public class Client {

    // non obligatoire car avec le fonctionnement de jpa il faudra éventuellement construire des instances sans no_client
    @Id
    @UuidGenerator
    private UUID noClient;

    @Column(nullable = false, length = 100)
    @NonNull private String nom;

    @Column(nullable = false,length = 100)
    @NonNull private String prenom;

    @Column(nullable = false,length = 150,unique = true)
    @NonNull private String email;

    @Column(length = 15)
    private String noTelephone;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true, optional = false,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "no_adresse")
    @NonNull
    private Adresse adresse;


    //@NonNull private Adresse adresse;

}
