package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.FormaPagamentoEmUsoException;
import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormaPagamentoService {
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;


    public List<FormaPagamento> listar() {
        return formaPagamentoRepository.findAll();
    }


    public FormaPagamento buscar(Long formaPagamentoId) {
        return formaPagamentoRepository.findById(formaPagamentoId)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
    }


    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.saveAndFlush(formaPagamento);
    }

    @Transactional
    public FormaPagamento atualizar(Long formaPagamentoId, FormaPagamento formaPagamento) {
        FormaPagamento formaPagamentoAtual = buscar(formaPagamentoId);
        formaPagamentoAtual.setDescricao(formaPagamento.getDescricao());
        return formaPagamentoRepository.saveAndFlush(formaPagamentoAtual);
    }


    @Transactional
    public void remover(Long formaPagamentoId) {
        try {
            FormaPagamento formaPagamento = buscar(formaPagamentoId);
            formaPagamentoRepository.delete(formaPagamento);
            formaPagamentoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new FormaPagamentoNaoEncontradaException(formaPagamentoId);
        } catch (DataIntegrityViolationException e) {
            throw new FormaPagamentoEmUsoException(formaPagamentoId);
        }

    }
}
