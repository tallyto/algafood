package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
