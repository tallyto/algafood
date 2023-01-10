package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class CadastroRestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    public Restaurante salvar(Restaurante restaurante) {
        try {
            Long cozinhaId = restaurante.getCozinha().getId();
            Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
            restaurante.setCozinha(cozinha);
            return restauranteRepository.save(restaurante);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public Restaurante buscar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId).orElseThrow(
            () -> new RestauranteNaoEncontradoException(
                restauranteId));
    }

    public Restaurante atualizar(Long restauranteId, Restaurante restaurante) {
        Restaurante restauranteAtual = buscar(restauranteId);

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco",
            "dataCadastro", "dataAtualizacao");

        return salvar(restauranteAtual);
    }

    public Restaurante atualizarParcial(Long restauranteId, Map<String, Object> campos) {
        Restaurante restauranteAtual = buscar(restauranteId);
        merge(campos, restauranteAtual);
        return atualizar(restauranteId, restauranteAtual);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            assert field != null;
            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }
}

