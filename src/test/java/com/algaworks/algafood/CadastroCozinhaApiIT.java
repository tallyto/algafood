package com.algaworks.algafood;

import com.algaworks.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaApiIT {

    @LocalServerPort
    private int port;

    @Autowired
    private Flyway flyway;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";
        flyway.migrate();
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
                .body("nome",  Matchers.hasSize(4))
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
}
