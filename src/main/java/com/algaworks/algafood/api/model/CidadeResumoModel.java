package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class CidadeResumoModel extends RepresentationModel<CidadeResumoModel> {
    private Long id;
    private String nome;
    private String estado;
}
