package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.DTO.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaModelAssembler {
    @Autowired
    ModelMapper modelMapper;

    public CozinhaModel toDTO(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaModel.class);
    }

    public List<CozinhaModel> toCollectionDTO(List<Cozinha> cozinhas) {
        return cozinhas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Cozinha toEntity(CozinhaInput cozinhaInput) {
        return modelMapper.map(cozinhaInput, Cozinha.class);
    }
}
