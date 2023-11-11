package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.LinkBuilder;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoResumoAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LinkBuilder linkBuilder;

    public PedidoResumoAssembler() {
        super(PedidoController.class, PedidoResumoModel.class);
    }

    @Override
    public PedidoResumoModel toModel(Pedido pedido) {
        PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoResumoModel);

        pedidoResumoModel.add(linkBuilder.linkToPedidos("pedidos"));

        pedidoResumoModel.getCliente().add(linkBuilder.linkToUsuario(pedidoResumoModel.getCliente().getId()));

        pedidoResumoModel.getRestaurante().add(linkBuilder.linkToRestaurante(pedidoResumoModel.getRestaurante().getId()));

        return pedidoResumoModel;
    }

    public Pedido toEntity(PedidoInput pedidoInput) {
        return modelMapper.map(pedidoInput, Pedido.class);
    }

}
