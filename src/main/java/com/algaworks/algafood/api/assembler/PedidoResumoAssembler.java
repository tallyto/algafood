package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.DTO.PedidoDTO;
import com.algaworks.algafood.api.model.DTO.PedidoResumoDTO;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoResumoAssembler {
    @Autowired
    ModelMapper modelMapper;

    public PedidoResumoDTO toDTO(Pedido pedido) {
        return modelMapper.map(pedido, PedidoResumoDTO.class);
    }

    public List<PedidoResumoDTO> toCollectionDTO(Collection<Pedido> pedidos) {
        return pedidos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Pedido toEntity(PedidoInput pedidoInput) {
        return modelMapper.map(pedidoInput, Pedido.class);
    }

}