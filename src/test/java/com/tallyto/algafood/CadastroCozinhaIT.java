package com.tallyto.algafood;

import com.tallyto.algafood.domain.exception.CozinhaEmUsoException;
import com.tallyto.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.tallyto.algafood.domain.model.Cozinha;
import com.tallyto.algafood.domain.model.Restaurante;
import com.tallyto.algafood.domain.service.CadastroCozinhaService;
import com.tallyto.algafood.domain.service.RestauranteService;
import com.tallyto.algafood.util.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT extends BaseTest {

    @Autowired
    CadastroCozinhaService cozinhaService;

    @Autowired
    RestauranteService restauranteService;

    @Test
    void shouldSucessoAoCadastrarCozinha() {
        // cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");
        // ação
        novaCozinha = cozinhaService.salvar(novaCozinha);

        // validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test
    void shouldSuccessOnUpdateCozinha() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Brasileira");
        cozinha = cozinhaService.salvar(cozinha);

        cozinha.setNome("Indiana");
        cozinha = cozinhaService.atualizar(cozinha.getId(), cozinha);

        assertThat(cozinha.getNome()).isEqualTo("Indiana");
    }

    @Test
    void shouldFailAoCadastrarCozinhaSemNome() {

        Cozinha cozinha = new Cozinha();
        cozinha.setNome(null);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> cozinhaService.salvar(cozinha));
    }

    @Disabled(value = "Teste quebrado")
    @Test
    void shouldFailAoExcluirCozinhaEmUso() {

        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Brasileira");
        cozinha = cozinhaService.salvar(cozinha);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Cumbuca do Chef");
        restaurante.setTaxaFrete(BigDecimal.valueOf(10));
        restaurante.setCozinha(cozinha);

        restauranteService.salvar(restaurante);

        Cozinha finalCozinha = cozinha;
        Assertions.assertThrows(CozinhaEmUsoException.class, () -> cozinhaService.excluir(finalCozinha.getId()));
    }

    @Test
    void shouldFailAoExcluirCozinhaSemId() {
        Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> cozinhaService.excluir(100L));
    }

}
