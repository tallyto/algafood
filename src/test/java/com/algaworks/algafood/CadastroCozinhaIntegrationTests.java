package com.algaworks.algafood;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class CadastroCozinhaIntegrationTests {

    @Autowired
    CadastroCozinhaService cozinhaService;

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
    public void shouldFailAoCadastrarCozinhaSemNome(){
        Assertions.assertThrows(ConstraintViolationException.class, ()-> {
            Cozinha cozinha = new Cozinha();
            cozinha.setNome(null);
            cozinhaService.salvar(cozinha);
        });
    }

}
