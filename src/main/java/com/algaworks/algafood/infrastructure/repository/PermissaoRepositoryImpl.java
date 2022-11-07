package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {
    @PersistenceContext // anotacao para injetar a dependencia do JPA
    private EntityManager manager;

    @Override
    public List<Permissao> listar() {
        TypedQuery<Permissao> query = manager.createQuery("from permissao", Permissao.class);
        return query.getResultList();
    }

    @Override
    public Permissao buscar(Long id) {
        return manager.find(Permissao.class, id);
    }

    @Override
    public Permissao salvar(Permissao permissao) {
        return manager.merge(permissao);
    }

    @Override
    public void remover(Permissao permissao) {
        permissao = buscar(permissao.getId());
        manager.remove(permissao);
    }
}
