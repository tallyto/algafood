package com.tallyto.algafood.domain.repository;

import com.tallyto.algafood.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
