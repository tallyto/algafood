package com.algaworks.algafood.api.model.input;

import com.algaworks.algafood.domain.model.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoInput {

    @Min(0)
    private BigDecimal subtotal;

    @Min(0)
    private BigDecimal taxaFrete;

    @Min(0)
    private BigDecimal valorTotal;

    @NotNull
    private List<ItemPedido> itens;

    @NotNull
    private FormaPagamento formaPagamento;

    @NotNull
    private Restaurante restaurante;

    @NotNull
    private Usuario cliente;
}
