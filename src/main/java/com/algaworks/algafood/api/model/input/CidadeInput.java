package com.algaworks.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {
    @ApiModelProperty(value = "Nome da cidade", example = "São Paulo")
    private String nome;
    private EstadoIdInput estado;
}
