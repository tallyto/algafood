package com.algaworks.algafood.api.model.DTO;

import com.algaworks.algafood.domain.model.Restaurante;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class RestauranteDTO {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinha;

    public static RestauranteDTO toDTO(Restaurante restaurante) {
        RestauranteDTO  restauranteDTO = new RestauranteDTO();
        CozinhaDTO cozinhaDTO  = new CozinhaDTO();
        restauranteDTO.setId(restaurante.getId());
        restauranteDTO.setNome(restaurante.getNome());
        restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());
        cozinhaDTO.setNome(restaurante.getCozinha().getNome());
        cozinhaDTO.setId(restaurante.getCozinha().getId());
        restauranteDTO.setCozinha(cozinhaDTO);
        return restauranteDTO;
    }

    public static List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes) {
        return restaurantes.stream().map(RestauranteDTO::toDTO).collect(Collectors.toList());
    }

}
