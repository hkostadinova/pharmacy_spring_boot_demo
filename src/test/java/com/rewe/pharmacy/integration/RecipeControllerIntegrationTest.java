package com.rewe.pharmacy.integration;

import com.rewe.pharmacy.data.entity.Recipe;
import com.rewe.pharmacy.data.repository.RecipeRepository;
import com.rewe.pharmacy.dto.RecipeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RecipeControllerIntegrationTest {

    @Container
    @ServiceConnection
    public static PostgreSQLContainer postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer(DockerImageName.parse("postgres:latest"))
            .withDatabaseName("integration-tests-db")
            .withUsername("test_user")
            .withPassword("test_user_pass")
//            .withInitScript("db-containers/init_postgres_container.sql")
            ;

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    private RecipeRepository recipeRepository;

    @BeforeEach
    void init() {
        this.recipeRepository.deleteAll();
    }

    @Test
    void connectionEstablished() {
        assertThat(postgreSQLContainer.isCreated()).isTrue();
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }

    @Test
    void shouldFindRecipeWhenValidRecipeID() {
        Recipe recipe1 = Recipe.builder()
                .creationDate(LocalDate.of(2025, 3, 3))
                .build();
        recipeRepository.save(recipe1);
        ResponseEntity<RecipeDTO> response = restTemplate
                .withBasicAuth("test_user", "doctor_user")
                .exchange("/api/recipes/1", HttpMethod.GET, new HttpEntity<>(recipe1), RecipeDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldCreateNewRecipeWhenRecipeIsValid() {
        RecipeDTO recipe1 = RecipeDTO.builder()
                .creationDate(LocalDate.of(2025, 3, 3))
                .build();
        ResponseEntity<RecipeDTO> response = restTemplate
                .withBasicAuth("test_user", "doctor_user")
                .exchange("/api/recipes", HttpMethod.POST, new HttpEntity<>(recipe1), RecipeDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCreationDate()).isEqualTo(LocalDate.of(2025, 3, 3));
    }
}

