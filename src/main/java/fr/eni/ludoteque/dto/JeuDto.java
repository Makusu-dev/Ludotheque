package fr.eni.ludoteque.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record JeuDto(
        @NotBlank
        @Size(min=1, max=50)
        String titre,
        @NotBlank
        @Size(min=1, max=50)
        String reference,
        @Min(2)
        Float tarifJour,
        @NotBlank
        @Size(min=1, max=200)
        String description,
        @Min(12)
        int ageMin,
        @Min(1)
        int duree,
        @NotNull
        List<String> genres,
        @Nullable
        Integer nbDisponibles
) {
}
