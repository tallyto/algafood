package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.PermissaoNaoEncontradaException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoService {
    @Autowired
    private PermissaoRepository permissaoRepository;

    public List<Permissao> listar(){
        return permissaoRepository.findAll();
    }

    public Permissao buscar(Long id){
        return permissaoRepository.findById(id).orElseThrow(()-> new PermissaoNaoEncontradaException(id));
    }
}
