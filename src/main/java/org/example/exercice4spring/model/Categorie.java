package org.example.exercice4spring.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Categorie {
    private UUID id;
    @NotBlank(message = "le nom est obligatoire")
    private String nom;
    private String description;


}
