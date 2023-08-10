package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    public Restaurante buscar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId).orElseThrow(
            () -> new RestauranteNaoEncontradoException(
                restauranteId));
    }

    @Transactional
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

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restauranteAtual = buscar(restauranteId);
        restauranteAtual.ativar();
        restauranteRepository.saveAndFlush(restauranteAtual);
    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restauranteAtual = buscar(restauranteId);
        restauranteAtual.inativar();
        restauranteRepository.saveAndFlush(restauranteAtual);
    }
}

