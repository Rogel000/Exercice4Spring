package org.example.exercice4spring.service;
import org.example.exercice4spring.model.Categorie;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoriesServiceImpl implements Repository<Categorie> {
    private final List<Categorie> categories = new ArrayList<>();


    public Categorie create(Categorie categorie) {
        categorie.setId(UUID.randomUUID());
        categories.add(categorie);
        return categorie;
    }


    public Categorie findById(UUID id) {
        return categories.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }


    public Categorie update(Categorie categorie) {
        Categorie existingCategory = findById(categorie.getId());
        if (existingCategory != null) {
            existingCategory.setNom(categorie.getNom());
            existingCategory.setDescription(categorie.getDescription());
        }
        return existingCategory;
    }


    public void delete(UUID id) {
        categories.removeIf(c -> c.getId().equals(id));
    }


    public List<Categorie> findAll() {
        return categories;
    }
}
