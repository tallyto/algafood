package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.DTO.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoModelAssembler {
    @Autowired
    ModelMapper modelMapper;

    public EstadoModel toDTO(Estado estado) {
        return modelMapper.map(estado, EstadoModel.class);
    }

    public List<EstadoModel> toCollectionDTO(List<Estado> estados) {
        return estados.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Estado toEntity(EstadoInput estadoInput) {
        return modelMapper.map(estadoInput, Estado.class);
    }
}
