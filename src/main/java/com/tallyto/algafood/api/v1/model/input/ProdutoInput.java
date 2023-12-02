package com.tallyto.algafood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoInput {
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    @Min(0)
    private BigDecimal preco;

    @NotNull
    private boolean ativo;
}
