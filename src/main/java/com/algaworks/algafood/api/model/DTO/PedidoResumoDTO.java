package com.algaworks.algafood.api.model.DTO;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@JsonFilter("pedidoFilter")
@Getter
@Setter
public class PedidoResumoDTO {
    private String codigo;

    private BigDecimal subtotal;

    private BigDecimal taxaFrete;

    private BigDecimal valorTotal;

    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataEntrega;

    private String status;

    private RestauranteResumoDTO restaurante;

    private UsuarioDTO cliente;
}
