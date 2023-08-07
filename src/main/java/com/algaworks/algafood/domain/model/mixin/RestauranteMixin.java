package com.algaworks.algafood.domain.model.mixin;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {
    // ignora o campo nome na criação de restaurante, mas permite que ele seja obtido
    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnore // não serializa o campo
    private List<FormaPagamento> formasPagamento = new ArrayList<>();
}
