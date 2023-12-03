package com.tallyto.algafood.api.v2.assembler;

import com.tallyto.algafood.api.v2.LinkBuilderV2;
import com.tallyto.algafood.api.v2.controller.CozinhaControllerV2;
import com.tallyto.algafood.api.v2.model.CozinhaModelV2;
import com.tallyto.algafood.api.v2.model.input.CozinhaInputV2;
import com.tallyto.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaModelAssemblerV2 extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModelV2> {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LinkBuilderV2 linkBuilder;

    public CozinhaModelAssemblerV2() {
        super(CozinhaControllerV2.class, CozinhaModelV2.class);
    }

    @Override
    public CozinhaModelV2 toModel(Cozinha cozinha) {
        CozinhaModelV2 cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaModel);

        cozinhaModel.add(linkBuilder.linkToCozinhas("cozinhas"));

        return cozinhaModel;
    }

    public List<CozinhaModelV2> toCollectionDTO(List<Cozinha> cozinhas) {
        return cozinhas.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Cozinha toEntity(CozinhaInputV2 cozinhaInput) {
        return modelMapper.map(cozinhaInput, Cozinha.class);
    }
}
