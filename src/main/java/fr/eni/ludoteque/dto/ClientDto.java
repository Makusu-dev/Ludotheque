package fr.eni.ludoteque.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClientDto(
        @NotBlank
        @Size(min=1, max=50)
        String nom,
        @NotBlank
        @Size(min=1, max=50)
        String prenom,
        @NotBlank
        @Size(min=1, max=50)
        @Email
        String email,
        @NotBlank
        @Size(min=1, max=50)
        String no_telephone,
        @NotBlank
        @Size(min=1, max=50)
        String rue,
        @NotBlank
        @Size(min=1, max=50)
        String codePostal,
        @NotBlank
        @Size(min=1, max=50)
        String ville) {
}
