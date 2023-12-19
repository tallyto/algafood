package com.tallyto.algafood;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tallyto.algafood.api.v1.model.input.CozinhaInput;
import com.tallyto.algafood.domain.model.Cozinha;
import com.tallyto.algafood.domain.repository.CozinhaRepository;
import com.tallyto.algafood.util.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@Disabled("Verificar como testar com authorization")
public class CadastroCozinhaApiIT extends BaseTest {

    public static final int COZINHA_ID_INEXISTENTE = 100;

    private final CozinhaInput cozinhaInput = new CozinhaInput();

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private CozinhaRepository cozinhaRepository;

    private Integer numeroCozinhas;

    private Cozinha cozinhaSalva;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "v1/cozinhas";
        prepareData();
        cozinhaInput.setNome("Americana");
    }

    @Test
    void shouldReturn200WhenConsultingCozinha() {
        RestAssured.given()
            .accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldContainCozinhaWhenConsultingCozinha() {
        RestAssured.given()
            .accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .body("_embedded.cozinhas", Matchers.hasSize(this.numeroCozinhas))
            .body("_embedded.cozinhas.nome", Matchers.containsInAnyOrder("Indiana", "Tailandesa"));
    }

    @Test
    void shouldReturn201WhenCreatingCozinha() throws JsonProcessingException {
        RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(objectMapper.writeValueAsString(cozinhaInput))
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void shouldReturn200WhenGetAnExistentCozinhaById() {
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
    void shouldReturn404WhenGetAnNonExistentCozinhaById() {
        RestAssured.given()
            .pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
            .accept(ContentType.JSON)
            .when()
            .get("/{cozinhaId}")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn200WhenUpdatingAnExistentCozinha() throws JsonProcessingException {

        RestAssured.given()
            .pathParam("cozinhaId", cozinhaSalva.getId())
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(objectMapper.writeValueAsString(cozinhaInput))
            .when()
            .put("/{cozinhaId}")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", Matchers.equalTo(cozinhaInput.getNome()));
    }

    @Test
    void shouldReturn404WhenUpdatingANonExistentCozinha() throws JsonProcessingException {
        RestAssured.given()
            .pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(objectMapper.writeValueAsString(cozinhaInput))
            .when()
            .put("/{cozinhaId}")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn204WhenDeletingAnExistentCozinha() {
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
