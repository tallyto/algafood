package com.algaworks.algafood;

import com.algaworks.algafood.domain.exception.CozinhaEmUsoException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

    @Autowired
    CadastroCozinhaService cozinhaService;

    @Autowired
    CadastroRestauranteService restauranteService;

    @Test
    public void shouldSucessoAoCadastrarCozinha() {
        // cenarario
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");
        // acao
        novaCozinha = cozinhaService.salvar(novaCozinha);

        // validacao
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test
    public void shouldSuccessOnUpdateCozinha() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Brasileira");
        cozinha = cozinhaService.salvar(cozinha);

        cozinha.setNome("Indiana");
        cozinha = cozinhaService.atualizar(cozinha.getId(), cozinha);

        assertThat(cozinha.getNome()).isEqualTo("Indiana");
    }

    @Test
    public void shouldFailAoCadastrarCozinhaSemNome() {

        Cozinha cozinha = new Cozinha();
        cozinha.setNome(null);

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            cozinhaService.salvar(cozinha);
        });
    }

    @Test
    public void shouldFailAoExcluirCozinhaEmUso() {

        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Brasileira");
        cozinha = cozinhaService.salvar(cozinha);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Cumbuca do Chef");
        restaurante.setTaxaFrete(BigDecimal.valueOf(10));
        restaurante.setCozinha(cozinha);

        restauranteService.salvar(restaurante);

        Cozinha finalCozinha = cozinha;
        Assertions.assertThrows(CozinhaEmUsoException.class, () -> {
            cozinhaService.excluir(finalCozinha.getId());
        });
    }

    @Test
    public void shouldFailAoExcluirCozinhaSemId() {
        Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
            cozinhaService.excluir(100L);
        });
    }

}
