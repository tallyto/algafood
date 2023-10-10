package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.DTO.FotoProdutoModel;
import com.algaworks.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FotoProdutoAssembler {
    @Autowired
    ModelMapper modelMapper;

    public FotoProdutoModel toDTO(FotoProduto fotoProduto) {
        return modelMapper.map(fotoProduto, FotoProdutoModel.class);
    }

    public List<FotoProdutoModel> toCollectionDTO(Collection<FotoProduto> fotosProduto) {
        return fotosProduto.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
