package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Pessoa;

import java.util.List;

public interface PessoaRepository {
    List<Pessoa> listar();

    Pessoa buscar(Long id);

    Pessoa salvar(Pessoa pessoa);

    void remover(Pessoa pessoa);
}
