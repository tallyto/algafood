package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.LinkBuilder;
import com.algaworks.algafood.api.v1.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.input.ProdutoInput;
import com.algaworks.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoModelAssembler
    extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LinkBuilder linkBuilder;

    public ProdutoModelAssembler() {
        super(RestauranteProdutoController.class, ProdutoModel.class);
    }

    @Override
    public ProdutoModel toModel(Produto produto) {
        ProdutoModel produtoModel = createModelWithId(
            produto.getId(), produto, produto.getRestaurante().getId());

        modelMapper.map(produto, produtoModel);

        produtoModel.add(linkBuilder.linkToProdutos(produto.getRestaurante().getId(), "produtos"));

        produtoModel.add(linkBuilder.linkToFotoProduto(
            produto.getRestaurante().getId(), produto.getId(), "foto"));

        return produtoModel;
    }

    public Produto toEntity(ProdutoInput produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }

    public List<Produto> toCollectionEntity(List<ProdutoModel> produtoDTOS) {
        return produtoDTOS.stream().map((element) -> modelMapper.map(element, Produto.class)).collect(Collectors.toList());
    }
}
