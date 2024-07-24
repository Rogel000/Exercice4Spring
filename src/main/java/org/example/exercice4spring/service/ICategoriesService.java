package org.example.exercice4spring.service;

import org.example.exercice4spring.model.Categorie;



import java.util.List;
import java.util.UUID;

public interface ICategoriesService {
    Categorie createCategory(Categorie categorie);
    Categorie getCategoryById(UUID id);
    void deleteCategory(UUID id);
    Categorie updateCategory(Categorie categorie);
    List<Categorie> getAllCategories();
}
