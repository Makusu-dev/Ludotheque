package fr.eni.ludoteque.dto;

public record ClientDto(String nom,
                        String prenom,
                        String email,
                        String no_telephone,
                        String rue,
                        String code_postal,
                        String ville) {
}
