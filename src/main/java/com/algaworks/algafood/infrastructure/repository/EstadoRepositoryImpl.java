package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class EstadoRepositoryImpl implements EstadoRepository {
    @PersistenceContext // anotacao para injetar a dependencia do JPA
    private EntityManager manager;

    @Override
    public List<Estado> listar() {
        TypedQuery<Estado> query = manager.createQuery("from Estado", Estado.class);
        return query.getResultList();
    }

    @Override
    public Estado buscar(Long id) {
        return manager.find(Estado.class, id);
    }

    @Transactional // Adiciona e atualiza um objeto no banco de dados
    @Override
    public Estado salvar(Estado estado) {
        return manager.merge(estado);
    }

    @Transactional // (@Transactional) -> Executa o metodo dentro de uma transação
    @Override
    public void remover(Estado estado) {
        estado = buscar(estado.getId());
        manager.remove(estado);
    }
}
