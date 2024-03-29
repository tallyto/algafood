package com.tallyto.algafood.domain.service;

import com.tallyto.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.tallyto.algafood.domain.exception.NegocioException;
import com.tallyto.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.tallyto.algafood.domain.model.*;
import com.tallyto.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private UsuarioService usuarioService;

    public Restaurante buscar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId).orElseThrow(
            () -> new RestauranteNaoEncontradoException(restauranteId));
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        try {
            Long cozinhaId = restaurante.getCozinha().getId();
            Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
            restaurante.setCozinha(cozinha);
            Long cidadeId = restaurante.getEndereco().getCidade().getId();
            Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);
            restaurante.getEndereco().setCidade(cidade);
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

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscar(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
        restaurante.desassociarFormaPagamento(formaPagamento);
        restauranteRepository.saveAndFlush(restaurante);
    }

    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscar(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
        restaurante.associarFormaPagamento(formaPagamento);
        restauranteRepository.saveAndFlush(restaurante);
    }

    @Transactional
    public void desassociarUsuarioResponsavel(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = buscar(restauranteId);
        Usuario usuario = usuarioService.buscar(usuarioId);
        restaurante.desassociarUsuarioResponsavel(usuario);
        restauranteRepository.saveAndFlush(restaurante);
    }

    @Transactional
    public void associarUsuarioResponsavel(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = buscar(restauranteId);
        Usuario usuario = usuarioService.buscar(usuarioId);
        restaurante.associarUsuarioResponsavel(usuario);
        restauranteRepository.saveAndFlush(restaurante);
    }

    @Transactional
    public void abertura(Long restauranteId) {
        Restaurante restauranteAtual = buscar(restauranteId);
        restauranteAtual.abertura();
        restauranteRepository.saveAndFlush(restauranteAtual);
    }

    @Transactional
    public void fechamento(Long restauranteId) {
        Restaurante restauranteAtual = buscar(restauranteId);
        restauranteAtual.fechamento();
        restauranteRepository.saveAndFlush(restauranteAtual);
    }

    @Transactional
    public void ativar(List<Long> restauranteIds) {
        restauranteIds.forEach(this::ativar);
    }

    @Transactional
    public void inativar(List<Long> restauranteIds) {
        restauranteIds.forEach(this::inativar);
    }
}

