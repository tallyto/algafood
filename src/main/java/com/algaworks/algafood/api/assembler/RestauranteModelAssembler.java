package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.DTO.RestauranteDTO;
import com.algaworks.algafood.api.model.input.RestauranteInput;
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
    public  RestauranteDTO toDTO(Restaurante restaurante) {
      return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    public  List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes) {
        return restaurantes.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public  Restaurante toEntity(RestauranteInput restauranteInput) {
        return modelMapper.map(restauranteInput, Restaurante.class);
    }
}
