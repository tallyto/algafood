package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.DTO.ProdutoDTO;
import com.algaworks.algafood.api.model.input.ProdutoInput;
import com.algaworks.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoAssembler {
    @Autowired
    ModelMapper modelMapper;

    public ProdutoDTO toDTO(Produto produto) {
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public List<ProdutoDTO> toCollectionDTO(List<Produto> produtos) {
        return produtos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Produto toEntity(ProdutoInput produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }

    public List<Produto> toCollectionEntity(List<ProdutoDTO> produtoDTOS) {
        return produtoDTOS.stream().map((element) -> modelMapper.map(element, Produto.class)).collect(Collectors.toList());
    }
}
