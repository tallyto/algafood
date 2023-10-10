package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.DTO.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoAssembler {

    @Autowired
    ModelMapper modelMapper;

    public GrupoModel toDTO(Grupo grupo) {
        return modelMapper.map(grupo, GrupoModel.class);
    }

    public List<GrupoModel> toCollectionDTO(Collection<Grupo> grupos) {
        return grupos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Grupo toEntity(GrupoInput grupoInput) {
        return modelMapper.map(grupoInput, Grupo.class);
    }
}
