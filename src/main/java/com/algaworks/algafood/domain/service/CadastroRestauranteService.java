package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    public Restaurante atualizarParcial(Long restauranteId, Restaurante restaurante) {
        return atualizar(restauranteId, restaurante);
    }
}

