package com.algaworks.algafood.api.model.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class EstadoDTO extends RepresentationModel<EstadoDTO> {
    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Minas Gerais")
    private String nome;
}
