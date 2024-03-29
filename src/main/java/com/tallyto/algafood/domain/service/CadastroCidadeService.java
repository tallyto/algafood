package com.tallyto.algafood.domain.service;

import com.tallyto.algafood.domain.exception.CidadeEmUsoException;
import com.tallyto.algafood.domain.exception.CidadeNaoEncontradaException;
import com.tallyto.algafood.domain.exception.EstadoNaoEncontradoException;
import com.tallyto.algafood.domain.exception.NegocioException;
import com.tallyto.algafood.domain.model.Cidade;
import com.tallyto.algafood.domain.model.Estado;
import com.tallyto.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroCidadeService {
    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoService cadastroEstadoService;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId).orElseThrow(
            () -> new CidadeNaoEncontradaException(cidadeId));
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        try {
            Long estadoId = cidade.getEstado().getId();
            Estado estado = cadastroEstadoService.buscar(estadoId);
            cidade.setEstado(estado);
            return cidadeRepository.save(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Transactional
    public void remover(Long id) {
        try {
            cidadeRepository.deleteById(id);
            cidadeRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(id);
        } catch (DataIntegrityViolationException e) {
            throw new CidadeEmUsoException(id);
        }
    }

    @Transactional
    public Cidade atualizar(Long id, Cidade cidade) {
        Cidade cidadeAtual = buscarOuFalhar(id);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        return salvar(cidadeAtual);
    }
}
