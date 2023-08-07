package com.algaworks.algafood;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaApiIT {

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";
        databaseCleaner.clearTables();
        prepareData();
    }

    @Test
    public void shouldReturn200WhenConsultingCozinha() {
        RestAssured.given()
            .accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldContainCozinhaWhenConsultingCozinha() {
        RestAssured.given()
            .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .body("nome", Matchers.hasSize(2))
                .body("nome", Matchers.hasItems("Indiana", "Tailandesa"));
    }

    @Test
    public void shouldReturn201WhenCreatingCozinha() throws JsonProcessingException {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Teste");

        RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(objectMapper.writeValueAsString(novaCozinha))
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldReturn200WhenGetAnExistentCozinhaById() {
        RestAssured.given()
            .pathParam("cozinhaId", 2)
            .accept(ContentType.JSON)
            .when()
                .get("/{cozinhaId}")
            .then()
                .body("nome", Matchers.equalTo("Indiana"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturn404WhenGetAnNonExistentCozinhaById() {
        RestAssured.given()
            .pathParam("cozinhaId", 100)
            .accept(ContentType.JSON)
            .when()
                .get("/{cozinhaId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepareData() {
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        cozinhaRepository.save(cozinha1);
        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Indiana");
        cozinhaRepository.save(cozinha2);
    }
}
