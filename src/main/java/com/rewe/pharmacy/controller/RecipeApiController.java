package com.rewe.pharmacy.controller;

import com.rewe.pharmacy.data.entity.Recipe;
import com.rewe.pharmacy.dto.RecipeDTO;
import com.rewe.pharmacy.dto.RecipeWithMedicinesDTO;
import com.rewe.pharmacy.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipes")
public class RecipeApiController {
    private final RecipeService recipeService;

    @GetMapping
    @PreAuthorize("hasAuthority('doctor')")
    public List<RecipeDTO> getRecipes() {
        return this.recipeService.getRecipes();
    }

    @GetMapping("/{id}")
    public RecipeDTO getRecipe(@PathVariable long id) {
        return this.recipeService.getRecipe(id);
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.createRecipe(recipe);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable long id) {
        return this.recipeService.updateRecipe(recipe, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable long id) {
        this.recipeService.deleteRecipe(id);
    }

    @GetMapping("/creation-date/{creationDate}/id/{id}")
    public List<Recipe> getAllRecipesByCreationDateAndDoctorId(@PathVariable LocalDate creationDate,
                                                               @PathVariable long id) {
        return this.recipeService.getAllRecipesByCreationDateAndDoctorId(creationDate, id);
    }

    @GetMapping("/creation-date/{creationDate}/doctor-name-contains/{doctorName}")
    public List<Recipe> getAllRecipesByCreationDateAndDoctorId(@PathVariable LocalDate creationDate,
                                                               @PathVariable String doctorName) {
        return this.recipeService.getAllRecipesByCreationDateAndDoctorNameContains(creationDate, doctorName);
    }

    @GetMapping("/recipe-with-medicines/{id}")
    public RecipeWithMedicinesDTO getRecipeByIdWithMedicines(@PathVariable long id) {
        return this.recipeService.findByRecipeIdWithMedicines(id);
    }
}


