package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CozinhaEmUsoException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(long cozinhaId) {
        try {
            cozinhaRepository.deleteById(cozinhaId);
        } catch (EmptyResultDataAccessException e) {
            throw new CozinhaNaoEncontradaException(cozinhaId);
        } catch (DataIntegrityViolationException e) {
            throw new CozinhaEmUsoException(cozinhaId);
        }
    }

    public Cozinha buscarOuFalhar(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId)
            .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
    }

    public Cozinha atualizar(long cozinhaId, Cozinha cozinha) {
        Cozinha cozinhaAtual = buscarOuFalhar(cozinhaId);
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return salvar(cozinhaAtual);
    }
}
