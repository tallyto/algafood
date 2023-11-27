package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.LinkBuilder;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.api.v1.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {


    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LinkBuilder linkBuilder;

    public RestauranteModelAssembler() {
        super(RestauranteController.class, RestauranteModel.class);
    }

    @Override
    public RestauranteModel toModel(Restaurante restaurante) {
        RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteModel);

        restauranteModel.add(linkBuilder.linkToRestaurantes("restaurantes"));

        if (restaurante.ativacaoPermitida()) {
            restauranteModel.add(linkBuilder.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
        }

        if (restaurante.inativacaoPermitida()) {
            restauranteModel.add(linkBuilder.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
        }

        if (restaurante.aberturaPermitida()) {
            restauranteModel.add(linkBuilder.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
        }

        if (restaurante.fechamentoPermitido()) {
            restauranteModel.add(linkBuilder.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
        }

        restauranteModel.add(linkBuilder.linkToProdutos(restaurante.getId(), "produtos"));

        restauranteModel.getCozinha().add(linkBuilder.linkToCozinha(restaurante.getCozinha().getId()));

        if (restauranteModel.getEndereco() != null
            && restauranteModel.getEndereco().getCidade() != null) {
            restauranteModel.getEndereco().getCidade().add(
                linkBuilder.linkToCidade(restaurante.getEndereco().getCidade().getId()));
        }

        restauranteModel.add(linkBuilder.linkToRestauranteFormasPagamento(restaurante.getId(), "formas-pagamento"));

        restauranteModel.add(linkBuilder.linkToRestauranteResponsaveis(restaurante.getId(), "responsaveis"));

        return restauranteModel;
    }

    @Override
    public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities).add(linkBuilder.linkToRestaurantes());
    }


    public Restaurante toEntity(RestauranteInput restauranteInput) {
        return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToEntity(RestauranteInput restauranteInput, Restaurante restaurante) {
        restaurante.setCozinha(new Cozinha()); // para evitar tentar alterar o id da cozinha
        if (restaurante.getEndereco() != null) {
            restaurante.getEndereco().setCidade(new Cidade());
        }
        modelMapper.map(restauranteInput, restaurante);
    }
}
