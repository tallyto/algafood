package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean // This annotation is used to prevent Spring from creating a bean for this interface
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {
    Optional<T> buscarPrimeiro();

}
