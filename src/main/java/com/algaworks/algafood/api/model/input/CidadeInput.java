package com.algaworks.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {
    private String nome;
    private EstadoIdInput estado;
}
