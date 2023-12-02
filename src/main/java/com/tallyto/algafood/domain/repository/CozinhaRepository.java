package com.tallyto.algafood.domain.repository;

import com.tallyto.algafood.domain.model.Cozinha;

import java.util.List;
import java.util.Optional;
// Repositorio orientado a persistencia

public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {
    List<Cozinha> findAllByNome(String nome);

    List<Cozinha> findByNomeContaining(String nome);

    Optional<Cozinha> findByNome(String nome);

    boolean existsByNome(String nome);

}



