package com.tallyto.algafood.api.v1.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tallyto.algafood.domain.model.Cozinha;
import com.tallyto.algafood.domain.model.Endereco;
import com.tallyto.algafood.domain.model.FormaPagamento;
import com.tallyto.algafood.domain.model.Produto;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {
    // ignora o campo nome na criação de restaurante, mas permite que ele seja obtido
    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private OffsetDateTime dataCadastro;

    @JsonIgnore
    private OffsetDateTime dataAtualizacao;

    @JsonIgnore // não serializa o campo
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();
}
