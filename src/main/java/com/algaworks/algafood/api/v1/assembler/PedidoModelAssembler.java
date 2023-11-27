package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.LinkBuilder;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
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

        pedidoModel.add(linkBuilder.linkToPedidos("pedidos"));

        if (pedido.podeSerConfirmado()) {
            pedidoModel.add(linkBuilder.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
        }

        if (pedido.podeSerEntregue()) {
            pedidoModel.add(linkBuilder.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
        }

        if (pedido.podeSerCancelado()) {
            pedidoModel.add(linkBuilder.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
        }

        pedidoModel.getCliente().add(linkBuilder.linkToUsuario(pedido.getCliente().getId()));

        pedidoModel.getRestaurante().add(linkBuilder.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoModel.getEnderecoEntrega().getCidade().add(linkBuilder.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

        pedidoModel.getFormaPagamento().add(linkBuilder.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

        pedidoModel.getItens().forEach(itemPedido ->
            itemPedido.add(linkBuilder.linkToProduto(pedido.getRestaurante().getId(), itemPedido.getProdutoId())));

        return pedidoModel;
    }

    public List<PedidoModel> toCollectionModel(Collection<Pedido> pedidos) {
        return pedidos.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Pedido toEntity(PedidoInput pedidoInput) {
        return modelMapper.map(pedidoInput, Pedido.class);
    }

}
