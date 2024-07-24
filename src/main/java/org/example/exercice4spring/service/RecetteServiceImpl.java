package org.example.exercice4spring.service;

import org.example.exercice4spring.model.Categorie;
import org.example.exercice4spring.model.Recette;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecetteServiceImpl implements Repository<Recette> {
    private final CategoriesServiceImpl categoriesServiceImpl;
    public List<Recette> recettes = new ArrayList<Recette>();

    public RecetteServiceImpl(CategoriesServiceImpl categoriesServiceImpl) {
        this.categoriesServiceImpl = categoriesServiceImpl;
    }


    public Recette create(Recette recette) {
        recette.setId(UUID.randomUUID());
        Categorie categorie = categoriesServiceImpl.findById(recette.getCategorie().getId());
        if(categorie == null) throw new IllegalStateException("Categorie not found");
        recette.setCategorie(categorie);
        recettes.add(recette);
        return recette;
    }


    public Recette findById(UUID id) {
        return recettes.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }


    public Recette update(Recette recette) {
        Recette existingRecette = findById(recette.getId());
        if(existingRecette != null) {
            existingRecette.setNom(recette.getNom());
            existingRecette.setIngredients(recette.getIngredients());
            Categorie categorie = categoriesServiceImpl.findById(recette.getCategorie().getId());
            if(categorie == null) throw new IllegalStateException("Categorie not found");
            existingRecette.setCategorie(categorie);
            existingRecette.setInstructions(recette.getInstructions());
        }
        return existingRecette;

    }

    public void delete(UUID id) {
        recettes.removeIf(c -> c.getId().equals(id));
    }

    public List<Recette> findAll() {
        return recettes;
    }
}
