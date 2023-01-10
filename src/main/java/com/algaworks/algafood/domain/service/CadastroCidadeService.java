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
        return cidadeRepository.findAll();
    }

    public Cidade buscarOuFalhar(Long id) {
        return cidadeRepository.findById(id).orElseThrow(
            () -> new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cidade com código %d", id)));
    }

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstadoService.buscar(estadoId);
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public void remover(Long id) {
        Cidade cidade = buscarOuFalhar(id);
        cidadeRepository.delete(cidade);
    }

    public Cidade atualizar(Long id, Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstadoService.buscar(estadoId);
        cidade.setEstado(estado);
        Cidade cidadeAtual = buscarOuFalhar(id);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        return salvar(cidadeAtual);
    }


}
