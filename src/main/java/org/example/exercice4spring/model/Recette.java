package org.example.exercice4spring.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recette {
    private UUID id;
    @NotBlank(message = "le nom est obligatoire")
    private String nom;
    private String ingredients;
    private String instructions;
    private Categorie categorie;


}
