package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.DTO.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeModelAssembler {
    @Autowired
    ModelMapper modelMapper;


    public CidadeModel toDTO(Cidade cidade) {
        return modelMapper.map(cidade, CidadeModel.class);
    }


    public List<CidadeModel> toCollectionDTO(List<Cidade> cidades) {
        return cidades.stream().map(this::toDTO).collect(Collectors.toList());
    }


    public Cidade toEntity(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }


}
