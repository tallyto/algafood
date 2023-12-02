package com.tallyto.algafood;

import com.tallyto.algafood.domain.model.Cozinha;
import com.tallyto.algafood.domain.repository.CozinhaRepository;
import com.tallyto.algafood.util.DatabaseCleaner;
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

    public static final int COZINHA_ID_INEXISTENTE = 100;

    private final Cozinha cozinhaDefault = new Cozinha("Americana");

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private Integer numeroCozinhas;

    private Cozinha cozinhaSalva;

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
            .body("nome", Matchers.hasSize(this.numeroCozinhas))
            .body("nome", Matchers.hasItems("Indiana", "Tailandesa"));
    }

    @Test
    public void shouldReturn201WhenCreatingCozinha() throws JsonProcessingException {
        RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(objectMapper.writeValueAsString(cozinhaDefault))
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldReturn200WhenGetAnExistentCozinhaById() {
        RestAssured.given()
            .pathParam("cozinhaId", cozinhaSalva.getId())
            .accept(ContentType.JSON)
            .when()
            .get("/{cozinhaId}")
            .then()
            .body("nome", Matchers.equalTo(cozinhaSalva.getNome()))
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturn404WhenGetAnNonExistentCozinhaById() {
        RestAssured.given()
            .pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
            .accept(ContentType.JSON)
            .when()
            .get("/{cozinhaId}")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void shouldReturn200WhenUpdatingAnExistentCozinha() throws JsonProcessingException {

        RestAssured.given()
            .pathParam("cozinhaId", cozinhaSalva.getId())
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(objectMapper.writeValueAsString(cozinhaDefault))
            .when()
            .put("/{cozinhaId}")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", Matchers.equalTo(cozinhaDefault.getNome()));
    }

    @Test
    public void shouldReturn404WhenUpdatingANonExistentCozinha() throws JsonProcessingException {
        RestAssured.given()
            .pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(objectMapper.writeValueAsString(cozinhaDefault))
            .when()
            .put("/{cozinhaId}")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void shouldReturn204WhenDeletingAnExistentCozinha() {
        RestAssured.given()
            .pathParam("cozinhaId", cozinhaSalva.getId())
            .accept(ContentType.JSON)
            .when()
            .delete("/{cozinhaId}")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    private void prepareData() {
        this.numeroCozinhas = 2;
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        cozinhaRepository.save(cozinha1);
        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Indiana");
        cozinhaSalva = cozinhaRepository.save(cozinha2);
    }
}
