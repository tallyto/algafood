package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.DTO.RestauranteDTO;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler {


    @Autowired
    ModelMapper modelMapper;

    public RestauranteDTO toDTO(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    public List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes) {
        return restaurantes.stream().map(this::toDTO).collect(Collectors.toList());
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
