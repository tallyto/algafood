package com.algaworks.algafood.api.model.DTO;

import com.algaworks.algafood.domain.model.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    private Long id;

    private BigDecimal subtotal;

    private BigDecimal taxaFrete;

    private BigDecimal valorTotal;

    private OffsetDateTime dataConfirmacao;

    private OffsetDateTime dataCancelamento;

    private OffsetDateTime dataEntrega;

    private StatusPedido status = StatusPedido.CRIADO;

    private List<ItemPedidoDTO> itensPedido;

    private FormaPagamento formaPagamento;

    private RestauranteResumoDTO restaurante;

    private UsuarioDTO cliente;
}
