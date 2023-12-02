package com.tallyto.algafood.api.v1.model;

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
