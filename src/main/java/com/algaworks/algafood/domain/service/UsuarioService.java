package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> listar () {
        return usuarioRepository.findAll();
    }

    public Usuario buscar(Long id){
        return usuarioRepository.findById(id).orElseThrow(()-> new UsuarioNaoEncontradoException(id));
    }
    @Transactional
    public Usuario criar(Usuario usuario){
        return usuarioRepository.saveAndFlush(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario usuario){
        Usuario usarioExistente = buscar(id);
        usarioExistente.setNome(usuario.getNome());
        usarioExistente.setEmail(usuario.getEmail());
        return usuarioRepository.saveAndFlush(usarioExistente);
    }

    @Transactional
    public void remover(Long id){
      try {
          Usuario usuarioExistente = buscar(id);
          usuarioRepository.delete(usuarioExistente);
          usuarioRepository.flush();
      } catch (EmptyResultDataAccessException e){
          log.error("usuario não encontrado", e);
          throw new UsuarioNaoEncontradoException(id);
      }
    }

}
