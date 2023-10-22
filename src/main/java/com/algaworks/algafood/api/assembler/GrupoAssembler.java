package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.LinkBuilder;
import com.algaworks.algafood.api.controller.GrupoController;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoModel> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LinkBuilder linkBuilder;

    public GrupoAssembler(){
        super(GrupoController.class, GrupoModel.class);
    }

    public GrupoModel toModel(Grupo grupo) {
        GrupoModel grupoModel = createModelWithId(grupo.getId(), grupo);
        modelMapper.map(grupo, grupoModel);

        return grupoModel;
    }

    public List<GrupoModel> toCollectionModel(Collection<Grupo> grupos) {
        return grupos.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Grupo toEntity(GrupoInput grupoInput) {
        return modelMapper.map(grupoInput, Grupo.class);
    }
}
