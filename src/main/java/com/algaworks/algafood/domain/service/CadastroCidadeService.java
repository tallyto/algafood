package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroCidadeService {
    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    CadastroEstadoService cadastroEstadoService;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId).orElseThrow(
            () -> new CidadeNaoEncontradaException(cidadeId));
    }

    public Cidade salvar(Cidade cidade) {
        try {
            Long estadoId = cidade.getEstado().getId();
            Estado estado = cadastroEstadoService.buscar(estadoId);
            cidade.setEstado(estado);
            return cidadeRepository.save(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(),e);
        }
    }

    public void remover(Long id) {
        try {
            cidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(id);
        } catch (DataIntegrityViolationException e) {
            throw new CozinhaEmUsoException(id);
        }
    }

    public Cidade atualizar(Long id, Cidade cidade) {
        Cidade cidadeAtual = buscarOuFalhar(id);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        return salvar(cidadeAtual);
    }
}
