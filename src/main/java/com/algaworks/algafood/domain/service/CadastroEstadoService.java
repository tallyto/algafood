package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EstadoEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado atualizar(Long estadoId, Estado estado) {
        Estado estadoAtual = buscar(estadoId);
        estadoAtual.setNome(estado.getNome());
        return estadoRepository.save(estadoAtual);
    }

    public void excluir(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
        } catch (DataIntegrityViolationException e) {
            throw new EstadoEmUsoException(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(estadoId);
        }
    }

    public Estado buscar(Long estadoId) {
        return estadoRepository.findById(estadoId).orElseThrow(
            () -> new EstadoNaoEncontradoException(estadoId));
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }
}
