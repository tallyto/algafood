package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {
    @Autowired
    private EmissaoPedidoService pedidoService;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public void confirmar(Long pedidoId){
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
        if(!pedido.getStatus().equals(StatusPedido.CRIADO)){
            throw new NegocioException(String.format("Status do pedido %d não pode alterado de %s para %s", pedidoId,
                pedido.getStatus().getDescricao(), StatusPedido.CONFIRMADO.getDescricao()));
        }

        pedido.setStatus(StatusPedido.CONFIRMADO);
        pedido.setDataConfirmacao(OffsetDateTime.now());
        pedidoRepository.saveAndFlush(pedido);
    }


}
