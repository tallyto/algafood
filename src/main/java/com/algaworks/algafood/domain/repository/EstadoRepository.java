package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> listar();

    Estado buscar(Long id);

    Estado salvar(Estado cidade);

    void remover(Estado cidade);
}
