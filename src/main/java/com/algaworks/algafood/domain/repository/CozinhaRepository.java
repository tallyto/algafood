package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;

import java.util.List;
// Repositorio orientado a persistencia

public interface CozinhaRepository {

    List<Cozinha> listar();

    Cozinha buscar(Long id);

    Cozinha salvar(Cozinha cozinha);

    void remover(Cozinha cozinha);

}
