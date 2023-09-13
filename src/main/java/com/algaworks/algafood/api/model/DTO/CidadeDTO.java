package com.algaworks.algafood.api.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "Cidade", description = "Representação de uma cidade")
@Getter
@Setter
public class CidadeDTO {
    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(value = "Nome da cidade", example = "São Paulo")
    private String nome;
    @ApiModelProperty(value = "Estado da cidade")
    private EstadoDTO estado;
}
