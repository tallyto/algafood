package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel> {
    private Long id;
    private String descricao;
}
