package com.algaworks.algafood.api.model.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class GrupoModel extends RepresentationModel<GrupoModel> {
    private Long id;
    private String nome;
}
