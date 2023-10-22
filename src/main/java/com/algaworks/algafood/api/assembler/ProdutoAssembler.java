package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.ProdutoModel;
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

    public ProdutoModel toDTO(Produto produto) {
        return modelMapper.map(produto, ProdutoModel.class);
    }

    public List<ProdutoModel> toCollectionDTO(List<Produto> produtos) {
        return produtos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Produto toEntity(ProdutoInput produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }

    public List<Produto> toCollectionEntity(List<ProdutoModel> produtoDTOS) {
        return produtoDTOS.stream().map((element) -> modelMapper.map(element, Produto.class)).collect(Collectors.toList());
    }
}
