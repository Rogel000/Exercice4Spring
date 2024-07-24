package org.example.exercice4spring.controller;

import jakarta.validation.Valid;

import org.example.exercice4spring.model.Recette;
import org.example.exercice4spring.service.CategoriesServiceImpl;
import org.example.exercice4spring.service.RecetteServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/recette")
public class RecetteController {

  private final RecetteServiceImpl recetteService;
    private final CategoriesServiceImpl categorieService;

    public RecetteController(RecetteServiceImpl recetteService, CategoriesServiceImpl categorieService) {
        this.recetteService = recetteService;
        this.categorieService = categorieService;
    }


    @GetMapping("/lister")
    public String ListerRecette(Model model) {
        List<Recette> recettes = recetteService.findAll();
        model.addAttribute("recettes", recettes);
        return "recette/lister";
    }

    @GetMapping("/ajouter")
    public String ajouterRecette(Model model) {
        model.addAttribute("recette", new Recette());
        model.addAttribute("categories", categorieService.findAll());
        return "recette/ajouter";
    }

    @PostMapping("/ajouter")
    public String sauvegarderRecette(@Valid @ModelAttribute("recette") Recette recette, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categorieService.findAll());
            return "recette/ajouter";
        }
        recetteService.create(recette);
        return "redirect:/recette/lister";
    }

    @GetMapping("/modifier/{id}")
    public String modifierRecette(@PathVariable UUID id, Model model) {
        Recette recette = recetteService.findById(id);
        if (recette == null) {
            return "redirect:/recette/lister";
        }
        model.addAttribute("recette", recette);
        model.addAttribute("categories", categorieService.findAll());
        return "recette/ajouter";
    }

    @PostMapping("/modifier")
    public String sauvegarderModification(@Valid @ModelAttribute("recette") Recette recette, BindingResult result, Model model) {
        System.out.println(recette);
        if (result.hasErrors()) {
            model.addAttribute("categories", categorieService.findAll());
            return "recette/ajouter";
        }
        recetteService.update(recette);
        return "redirect:/recette/lister";
    }



    @GetMapping("/supprimer/{id}")
    public String supprimerRecette(@PathVariable UUID id) {
        recetteService.delete(id);
        return "redirect:/recette/lister";
    }
}
