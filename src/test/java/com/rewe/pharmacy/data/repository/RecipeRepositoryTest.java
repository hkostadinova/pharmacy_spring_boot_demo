package com.rewe.pharmacy.data.repository;

import com.rewe.pharmacy.data.entity.Recipe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class RecipeRepositoryTest {

    @Autowired
    RecipeRepository recipeRepository;

    @Test
    void findByCreationDateTest() {
        Recipe recipe1 = Recipe.builder()
                .creationDate(LocalDate.of(2024, 04, 04))
                .build();
        Recipe recipe2 = Recipe.builder()
                .creationDate(LocalDate.of(2024, 04, 04))
                .build();
        Recipe recipe3 = Recipe.builder()
                .creationDate(LocalDate.of(2024, 03, 03))
                .build();

        recipeRepository.saveAll(Arrays.asList(recipe1, recipe2, recipe3));

        List<Recipe> expectedRecipes = Arrays.asList(recipe1, recipe2);
        assertIterableEquals(expectedRecipes, recipeRepository
                .findAllByCreationDate(LocalDate.of(2024, 04, 04)));
    }

    @Test
    void findByCreationDate_thenEmptyListTest() {
        List<Recipe> expectedRecipes = Arrays.asList();
        assertIterableEquals(expectedRecipes, recipeRepository
                .findAllByCreationDate(LocalDate.of(2024, 04, 04)));
    }
}
