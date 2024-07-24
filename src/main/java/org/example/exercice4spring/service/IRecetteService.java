package org.example.exercice4spring.service;

import org.example.exercice4spring.model.Recette;

import java.util.List;
import java.util.UUID;

public interface IRecetteService {
Recette createRecette(Recette recette);
Recette getRecetteById(UUID id);
Recette updateRecette(Recette recette);
void deleteRecette(UUID id);
List<Recette> getRecetteList();


}
