package com.rewe.pharmacy.service.impl;

import com.rewe.pharmacy.data.entity.Recipe;
import com.rewe.pharmacy.data.repository.RecipeRepository;
import com.rewe.pharmacy.dto.RecipeDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class RecipeServiceImplUnitTest {
    @Mock
    RecipeRepository recipeRepository;
    @InjectMocks
    RecipeServiceImpl recipeService;
    @Test
    void recipeServiceGetRecipesTest() {
        Mockito.when(recipeRepository.findRecipes()).thenReturn(Arrays.asList(new RecipeDTO()));
        assertEquals(1, recipeService.getRecipes().size());
    }
}
