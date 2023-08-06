package com.algaworks.algafood;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.hamcrest.Matchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaApiIT {

    @LocalServerPort
    private int port;

    @Before(value = "")
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";
    };
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
            .basePath("/cozinhas")
            .port(port)
            .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .body("nome",  Matchers.hasSize(4))
                .body("nome", Matchers.hasItems("Indiana", "Tailandesa"));

    }
}
