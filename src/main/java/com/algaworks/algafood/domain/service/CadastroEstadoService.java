package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
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
        return estadoRepository.salvar(estado);
    }

    public Estado atualizar(Long estadoId, Estado estado) {
        Estado estadoAtual = buscar(estadoId);
        if (estadoAtual == null) {
            throw new EntidadeNaoEncontradaException("Não existe estado com o código " + estadoId);
        }
        estadoAtual.setNome(estado.getNome());
        return estadoRepository.salvar(estadoAtual);
    }

    public void excluir(Long estadoId) {
        try {
            Estado estado = buscar(estadoId);
            estadoRepository.remover(estado);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de estado com código %d", estadoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removida, pois está em uso", estadoId));
        }
    }

    public Estado buscar(Long estadoId) {
        Estado estado = estadoRepository.buscar(estadoId);
        if (estado == null) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de estado com código %d", estadoId));
        }
        return estado;
    }

    public List<Estado> listar() {
        return estadoRepository.listar();
    }


}
