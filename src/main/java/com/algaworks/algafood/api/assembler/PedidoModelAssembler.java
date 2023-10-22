package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.LinkBuilder;
import com.algaworks.algafood.api.controller.*;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LinkBuilder linkBuilder;

    public PedidoModelAssembler() {
        super(PedidoController.class, PedidoModel.class);
    }

    public PedidoModel toModel(Pedido pedido) {

        PedidoModel pedidoModel = createModelWithId(pedido.getId(), pedido);
        modelMapper.map(pedido, pedidoModel);

        pedidoModel.add(linkBuilder.linkToPedidos());

        pedidoModel.getCliente().add(linkBuilder.linkToUsuario(pedidoModel.getCliente().getId()));

        pedidoModel.getRestaurante().add(linkBuilder.linkToRestaurante(pedidoModel.getRestaurante().getId()));

        pedidoModel.getEnderecoEntrega().getCidade().add(linkBuilder.linkToCidade(pedidoModel.getEnderecoEntrega().getCidade().getId()));

        pedidoModel.getFormaPagamento().add(linkBuilder.linkToFormaPagamento(pedidoModel.getFormaPagamento().getId()));

        pedidoModel.getItens().forEach(itemPedidoModel ->
            itemPedidoModel.add(linkBuilder.linkToProduto(pedidoModel.getRestaurante().getId(), itemPedidoModel.getProdutoId())));

        return pedidoModel;
    }

    public List<PedidoModel> toCollectionModel(Collection<Pedido> pedidos) {
        return pedidos.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Pedido toEntity(PedidoInput pedidoInput) {
        return modelMapper.map(pedidoInput, Pedido.class);
    }

}
