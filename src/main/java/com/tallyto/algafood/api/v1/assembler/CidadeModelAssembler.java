package com.tallyto.algafood.api.v1.assembler;

import com.tallyto.algafood.api.v1.LinkBuilder;
import com.tallyto.algafood.api.v1.controller.CidadeController;
import com.tallyto.algafood.api.v1.model.CidadeModel;
import com.tallyto.algafood.api.v1.model.input.CidadeInput;
import com.tallyto.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {
    private final ModelMapper modelMapper;

    private final LinkBuilder linkBuilder;

    @Autowired
    public CidadeModelAssembler(ModelMapper modelMapper, LinkBuilder linkBuilder) {
        super(CidadeController.class, CidadeModel.class);

        this.linkBuilder = linkBuilder;
        this.modelMapper = modelMapper;
    }

    @Override
    public CidadeModel toModel(Cidade cidade) {
        CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);

        modelMapper.map(cidade, cidadeModel);

        cidadeModel.add(linkBuilder.linkToCidades("cidades"));

        cidadeModel.getEstado().add(linkBuilder.linkToEstado(cidadeModel.getEstado().getId()));

        return cidadeModel;
    }

    @Override
    public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities)
            .add(linkTo(CidadeController.class).withSelfRel());
    }

    public Cidade toEntity(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }

}
