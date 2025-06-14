package com.rewe.pharmacy.service.impl;

import com.rewe.pharmacy.data.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipeServiceImplMethodLevelSecurityTest {
    @MockitoBean
    RecipeRepository repository;
    @Autowired
    RecipeServiceImpl recipeService;
    @Test
    @WithMockUser(authorities = {"doctor"})
    void recipeServiceGetRecipesTest() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList());

        assertEquals(0, recipeService.getRecipes().size());
        assertDoesNotThrow(() -> recipeService.getRecipes());
    }

    @Test
    @WithAnonymousUser
    void getRecipesWithAnonymousUser() throws Exception {
        assertThrows(AccessDeniedException.class,
                () -> this.recipeService.getRecipes());
    }

    @Test
    @WithMockUser(authorities = {"seller"})
    void getRecipesWithSellerUser() throws Exception {
        assertThrows(AccessDeniedException.class,
                () -> this.recipeService.getRecipes());
    }

    @Test
    void getRecipesWithoutUser() throws Exception {
        assertThrows(AuthenticationCredentialsNotFoundException.class,
                () -> this.recipeService.getRecipes());
    }
}