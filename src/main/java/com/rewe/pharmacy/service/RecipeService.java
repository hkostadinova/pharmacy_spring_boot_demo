package com.rewe.pharmacy.service;

import com.rewe.pharmacy.data.entity.Recipe;
import com.rewe.pharmacy.dto.RecipeDTO;
import com.rewe.pharmacy.dto.RecipeWithMedicinesDTO;

import java.time.LocalDate;
import java.util.List;

public interface RecipeService {
    List<RecipeDTO> getRecipes();

    RecipeDTO getRecipe(long id);

    Recipe createRecipe(Recipe recipe);

    Recipe updateRecipe(Recipe recipe, long id);

    void deleteRecipe(long id);

    List<Recipe> getAllRecipesByCreationDateAndDoctorId(LocalDate creationDate, long id);

    List<Recipe> getAllRecipesByCreationDateAndDoctorNameContains(LocalDate creationDate, String doctorName);

    RecipeWithMedicinesDTO findByRecipeIdWithMedicines(long id);
}