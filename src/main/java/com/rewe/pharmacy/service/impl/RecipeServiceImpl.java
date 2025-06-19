package com.rewe.pharmacy.service.impl;

import com.rewe.pharmacy.data.entity.Recipe;
import com.rewe.pharmacy.data.repository.RecipeRepository;
import com.rewe.pharmacy.dto.RecipeDTO;
import com.rewe.pharmacy.dto.RecipeWithMedicinesDTO;
import com.rewe.pharmacy.service.RecipeService;
import com.rewe.pharmacy.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final MapperUtil mapperUtil;

    @Override
    @PreAuthorize("hasAuthority('doctor')")
    public List<RecipeDTO> getRecipes() {
        return this.recipeRepository.findRecipes();
    }

    @Override
    public RecipeDTO getRecipe(long id) {
        return this.recipeRepository.findRecipe(id);
    }

    @Override
    public Recipe createRecipe(Recipe recipe) {
        return this.recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, long id) {
        return this.recipeRepository.findById(id)
                .map(recipe1 -> {
                    recipe1.setCreationDate(recipe.getCreationDate());
                    return this.recipeRepository.save(recipe1);
                }).orElseGet(() ->
                        this.recipeRepository.save(recipe)
                );
    }

    @Override
    public void deleteRecipe(long id) {
        this.recipeRepository.deleteById(id);
    }

    @Override
    public List<Recipe> getAllRecipesByCreationDateAndDoctorId(LocalDate creationDate, long id) {
        return this.recipeRepository.findAllByCreationDateAndDoctorId(creationDate, id);
    }

    @Override
    public List<Recipe> getAllRecipesByCreationDateAndDoctorNameContains(LocalDate creationDate, String doctorName) {
        return this.recipeRepository.findAllByCreationDateAndDoctorNameContains(creationDate, doctorName);
    }

    @Override
    public RecipeWithMedicinesDTO findByRecipeIdWithMedicines(long id) {
        return mapperUtil.getModelMapper().map(this.recipeRepository.findByRecipeIdWithMedicines(id), RecipeWithMedicinesDTO.class);
    }
}
