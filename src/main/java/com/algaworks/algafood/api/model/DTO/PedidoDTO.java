package com.algaworks.algafood.api.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    private String codigo;

    private BigDecimal subtotal;

    private BigDecimal taxaFrete;

    private BigDecimal valorTotal;

    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataConfirmacao;

    private OffsetDateTime dataCancelamento;

    private OffsetDateTime dataEntrega;

    private String status;

    private List<ItemPedidoDTO> itensPedido;

    private FormaPagamentoDTO formaPagamento;

    private RestauranteResumoDTO restaurante;

    private UsuarioDTO cliente;
}
