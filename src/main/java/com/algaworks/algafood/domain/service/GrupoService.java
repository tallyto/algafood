package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    public List<Grupo> listar() {
        return grupoRepository.findAll();
    }

    public Grupo buscar(Long grupoId){
        return grupoRepository.findById(grupoId).orElseThrow(()-> new GrupoNaoEncontradoException(grupoId));
    }

    @Transactional
    public Grupo criar(Grupo grupo){
        return grupoRepository.saveAndFlush(grupo);
    }

    @Transactional
    public Grupo atualizar(Long grupoId, Grupo grupo) {
        Grupo grupoAtual = buscar(grupoId);
        grupoAtual.setNome(grupo.getNome());
        return grupoRepository.saveAndFlush(grupoAtual);
    }

    @Transactional
    public void remover(Long grupoId){
        Grupo grupo = buscar(grupoId);
        grupoRepository.delete(grupo);
        grupoRepository.flush();
    }

}
