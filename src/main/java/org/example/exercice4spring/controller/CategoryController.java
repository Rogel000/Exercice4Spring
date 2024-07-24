package org.example.exercice4spring.controller;

import jakarta.validation.Valid;
import org.example.exercice4spring.model.Categorie;
import org.example.exercice4spring.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final ICategoriesService categoryService;

    @Autowired
    public CategoryController(ICategoriesService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listerCategories(Model model) {
        List<Categorie> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories/list";
    }

    @GetMapping("/ajouter")
    public String ajouterCategorie(Model model) {
        model.addAttribute("category", new Categorie());
        return "categories/form";
    }

    @PostMapping("/ajouter")
    public String sauvegarderCategorie(@Valid @ModelAttribute("category") Categorie categorie, BindingResult result) {
        if (result.hasErrors()) {
            return "categories/form";
        }
        categoryService.createCategory(categorie);
        return "redirect:/categories";
    }

    @GetMapping("/modifier/{id}")
    public String modifierCategorie(@PathVariable UUID id, Model model) {
        Categorie category = categoryService.getCategoryById(id);
        if (category == null) {
            return "redirect:/categories";
        }
        model.addAttribute("category", category);
        return "categories/form";
    }

    @PostMapping("/modifier")
    public String sauvegarderModification(@Valid @ModelAttribute("category") Categorie category, BindingResult result) {
        if (result.hasErrors()) {
            return "categories/form";
        }
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimerCategorie(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
