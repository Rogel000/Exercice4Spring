package org.example.exercice4spring.service;

import org.example.exercice4spring.model.Categorie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoriesServiceImpl implements ICategoriesService {
    private List<Categorie> categories = new ArrayList<>();


    public Categorie createCategory(Categorie categorie) {
        categorie.setId(UUID.randomUUID());
        categories.add(categorie);
        return categorie;
    }


    public Categorie getCategoryById(UUID id) {
        return categories.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }


    public Categorie updateCategory(Categorie categorie) {
        Categorie existingCategory = getCategoryById(categorie.getId());
        if (existingCategory != null) {
            existingCategory.setNom(categorie.getNom());
            existingCategory.setDescription(categorie.getDescription());
        }
        return existingCategory;
    }


    public void deleteCategory(UUID id) {
        categories.removeIf(c -> c.getId().equals(id));
    }


    public List<Categorie> getAllCategories() {
        return categories;
    }
}
