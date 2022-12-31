package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroCidadeService {
    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    CadastroEstadoService cadastroEstadoService;

    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    public Cidade buscar(Long id) {
        Cidade cidade = cidadeRepository.buscar(id);
        if (cidade == null) {
            throw new EntidadeNaoEncontradaException("Não existe cidade com o código " + id);
        }
        return cidade;
    }

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstadoService.buscar(estadoId);
        cidade.setEstado(estado);
        return cidadeRepository.salvar(cidade);
    }

    public void remover(Long id) {
        Cidade cidade = buscar(id);
        cidadeRepository.remover(cidade);
    }

    public Cidade atualizar(Long id, Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        if(estadoId != null) {
            Estado estado = cadastroEstadoService.buscar(estadoId);
            cidade.setEstado(estado);
        }
        Cidade cidadeAtual = buscar(id);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        return salvar(cidadeAtual);
    }


}
