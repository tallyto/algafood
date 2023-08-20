package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
// Repositorio orientado a persistencia

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {
    List<Cozinha> findAllByNome(String nome);

    List<Cozinha> findByNomeContaining(String nome);

    Optional<Cozinha> findByNome(String nome);

    boolean existsByNome(String nome);

}



