package com.tallyto.algafood.api.v1.assembler;

import com.tallyto.algafood.api.v1.LinkBuilder;
import com.tallyto.algafood.api.v1.controller.RestauranteProdutoFotoController;
import com.tallyto.algafood.api.v1.model.FotoProdutoModel;
import com.tallyto.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class FotoProdutoModelAssembler
    extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LinkBuilder linkBuilder;

    public FotoProdutoModelAssembler() {
        super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
    }

    @Override
    public FotoProdutoModel toModel(FotoProduto foto) {
        FotoProdutoModel fotoProdutoModel = modelMapper.map(foto, FotoProdutoModel.class);

        fotoProdutoModel.add(linkBuilder.linkToFotoProduto(
            foto.getRestauranteId(), foto.getProduto().getId()));

        fotoProdutoModel.add(linkBuilder.linkToProduto(
            foto.getRestauranteId(), foto.getProduto().getId(), "produto"));

        return fotoProdutoModel;
    }

}
