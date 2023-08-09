package com.algaworks.algafood.api.model.input;

import com.algaworks.algafood.domain.model.Restaurante;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteInput {
    @NotNull
    @NotBlank
    private String nome;
    @PositiveOrZero
    @NotNull
    private BigDecimal taxaFrete;
    @Valid
    private CozinhaIdInput cozinha;

    public Restaurante toModel() {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(nome);
        restaurante.setTaxaFrete(taxaFrete);
        restaurante.setCozinha(cozinha.toModel());
        return restaurante;
    }
}
