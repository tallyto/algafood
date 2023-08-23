package com.algaworks.algafood.api.model.DTO;

import com.algaworks.algafood.domain.model.FormaPagamento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class PedidoResumoDTO {
    private Long id;

    private BigDecimal subtotal;

    private BigDecimal taxaFrete;

    private BigDecimal valorTotal;

    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataEntrega;

    private String status;

    private RestauranteResumoDTO restaurante;

    private UsuarioDTO cliente;
}
