package com.tallyto.algafood.domain.service;

import com.tallyto.algafood.domain.exception.GrupoEmUsoException;
import com.tallyto.algafood.domain.exception.GrupoNaoEncontradoException;
import com.tallyto.algafood.domain.model.Grupo;
import com.tallyto.algafood.domain.model.Permissao;
import com.tallyto.algafood.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PermissaoService permissaoService;

    public List<Grupo> listar() {
        return grupoRepository.findAll();
    }

    public Grupo buscar(Long grupoId) {
        return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }

    @Transactional
    public Grupo criar(Grupo grupo) {
        return grupoRepository.saveAndFlush(grupo);
    }

    @Transactional
    public Grupo atualizar(Long grupoId, Grupo grupo) {
        Grupo grupoAtual = buscar(grupoId);
        grupoAtual.setNome(grupo.getNome());
        return grupoRepository.saveAndFlush(grupoAtual);
    }

    @Transactional
    public void remover(Long grupoId) {
        try {
            Grupo grupo = buscar(grupoId);
            grupoRepository.delete(grupo);
            grupoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new GrupoEmUsoException(grupoId);
        }
    }

    @Transactional
    public void desassociarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = buscar(grupoId);
        Permissao permissao = permissaoService.buscar(permissaoId);
        grupo.desassociarPermissao(permissao);
        grupoRepository.saveAndFlush(grupo);
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = buscar(grupoId);
        Permissao permissao = permissaoService.buscar(permissaoId);
        grupo.associarPermissao(permissao);
        grupoRepository.saveAndFlush(grupo);
    }

}
