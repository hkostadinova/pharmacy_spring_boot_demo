package com.rewe.pharmacy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewe.pharmacy.data.entity.Recipe;
import com.rewe.pharmacy.dto.RecipeDTO;
import com.rewe.pharmacy.service.impl.RecipeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecipeApiController.class)
class RecipeApiControllerTest {

    @MockitoBean
    private RecipeServiceImpl recipeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(authorities = {"doctor"})
    void createRecipeTest() throws Exception {
        Recipe recipe1 = Recipe.builder()
                .creationDate(LocalDate.of(2024,01,01))
                .build();

        given(recipeService.createRecipe(any(Recipe.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/recipes")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recipe1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.creationDate", is(recipe1.getCreationDate().toString())));
    }

    @Test
    @WithMockUser(authorities = {"doctor"})
    void getRecipesTest() throws Exception {
        RecipeDTO recipe1 = RecipeDTO.builder()
                .id(1L)
                .creationDate(LocalDate.of(2024,01,01))
                .build();
        RecipeDTO recipe2 = RecipeDTO.builder()
                .id(2L)
                .creationDate(LocalDate.of(2024,01,01))
                .build();
        List<RecipeDTO> recipesExpected = Arrays.asList(recipe1, recipe2);

        given(recipeService.getRecipes()).willReturn(recipesExpected);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/recipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(recipe1.getId()), Long.class))
                .andExpect(jsonPath("$[0].creationDate", is("2024-01-01")))
                .andExpect(jsonPath("$[1].id", is(recipe2.getId()), Long.class))
                .andExpect(jsonPath("$[1].creationDate", is("2024-01-01")))
                .andDo(print());
    }

    @Test
    @WithMockUser(authorities = {"doctor"})
    void getRecipeByIdTest() throws Exception {
        RecipeDTO recipe2 = RecipeDTO.builder()
                .id(2)
                .creationDate(LocalDate.of(2024,01,01))
                .build();
        int recipeId = 2;
        given(recipeService.getRecipe(recipeId)).willReturn(recipe2);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/recipes/{recipeId}", recipeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(recipe2.getId()), Long.class))
                .andExpect(jsonPath("$.creationDate", is("2024-01-01")))
                .andDo(print());
    }
}