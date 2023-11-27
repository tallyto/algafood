package com.algaworks.algafood.api.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
@ApiModel(value = "Cidade", description = "Representação de uma cidade")
@Getter
@Setter
public class CidadeModel extends RepresentationModel<CidadeModel> {
    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(value = "Nome da cidade", example = "São Paulo")
    private String nome;
    @ApiModelProperty(value = "Estado da cidade")
    private EstadoModel estado;
}
