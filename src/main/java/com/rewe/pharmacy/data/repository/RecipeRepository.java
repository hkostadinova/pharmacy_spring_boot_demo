package com.rewe.pharmacy.data.repository;

import com.rewe.pharmacy.data.entity.Recipe;
import com.rewe.pharmacy.dto.RecipeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
   @Query(value = "SELECT r.id, r.creationDate FROM Recipe r")
   List<RecipeDTO> findRecipes();
   @Query(value = "SELECT r.id, r.creationDate FROM Recipe r WHERE id= :id")
   RecipeDTO findRecipe(long id);
   List<Recipe> findAllByCreationDateAndDoctorId(LocalDate dateCreation, long doctorId);
   List<Recipe> findAllByCreationDateAndDoctorNameContains(LocalDate dateCreation, String doctorName);
   List<Recipe> findAllByCreationDateAndDoctorNameStartsWith(LocalDate dateCreation, String doctorName);
   List<Recipe> findAllByCreationDate(LocalDate dateCreation);


}
