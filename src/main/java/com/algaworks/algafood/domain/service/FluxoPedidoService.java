package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FluxoPedidoService {
    @Autowired
    private EmissaoPedidoService pedidoService;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public void confirmar(Long pedidoId){
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
        pedido.confirmar();
        pedidoRepository.saveAndFlush(pedido);
    }

    @Transactional
    public void cancelar(Long pedidoId){
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
        pedido.cancelar();
        pedidoRepository.saveAndFlush(pedido);
    }

    @Transactional
    public void entregue(Long pedidoId){
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
        pedido.entregar();
        pedidoRepository.saveAndFlush(pedido);
    }


}
