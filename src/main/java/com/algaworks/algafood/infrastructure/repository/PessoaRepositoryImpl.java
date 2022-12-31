package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Pessoa;
import com.algaworks.algafood.domain.repository.PessoaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class PessoaRepositoryImpl implements PessoaRepository {
    @PersistenceContext // anotacao para injetar a dependencia do JPA
    private EntityManager manager;

    @Override
    public List<Pessoa> listar() {
        TypedQuery<Pessoa> query = manager.createQuery("from Pessoa", Pessoa.class);
        return query.getResultList();
    }

    @Override
    public Pessoa buscar(Long id) {
        return manager.find(Pessoa.class, id);
    }

    @Transactional // Adiciona e atualiza um objeto no banco de dados
    @Override
    public Pessoa salvar(Pessoa cidade) {
        return manager.merge(cidade);
    }

    @Transactional // (@Transactional) -> Executa o metodo dentro de uma transação
    @Override
    public void remover(Pessoa cidade) {
        cidade = buscar(cidade.getId());
        manager.remove(cidade);
    }
}
