package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.ItemPedidoRepository;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscar(Long pedidoId){
        return pedidoRepository.findById(pedidoId).orElseThrow(()-> new PedidoNaoEncontradoException(pedidoId));
    }

    @Transactional
    public Pedido criar(Pedido pedido){

        var itemsPedido = pedido.getItens();

        var pedidoSalvo = pedidoRepository.save(pedido);

        itemsPedido.forEach(value -> {
            value.setPedido(pedidoSalvo);
        });

        itemPedidoRepository.saveAll(itemsPedido);

        return pedidoSalvo;
    }


}
