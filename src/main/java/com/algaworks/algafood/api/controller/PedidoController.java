package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PedidoAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoAssembler;
import com.algaworks.algafood.api.model.DTO.PedidoDTO;
import com.algaworks.algafood.api.model.DTO.PedidoResumoDTO;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoAssembler assembler;

    @Autowired
    private PedidoResumoAssembler resumoAssembler;

    @GetMapping
    public List<PedidoResumoDTO> listar(){
        List<Pedido> pedidos = pedidoService.listar();
        return resumoAssembler.toCollectionDTO(pedidos);
    }

    @GetMapping("{pedidoId}")
    public PedidoDTO buscar(@PathVariable Long pedidoId){
        return assembler.toDTO(pedidoService.buscar(pedidoId));
    }

    @PostMapping
    public PedidoDTO criar(@RequestBody @Valid PedidoInput pedidoInput){
        var pedido = pedidoService.criar(assembler.toEntity(pedidoInput));
        return assembler.toDTO(pedido);
    }

}
