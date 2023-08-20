package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @Transactional
    public Usuario criar(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent()) {
            throw new NegocioException(String.format("Já existe um usuário cadastrado com o e-email %s", usuario.getEmail()));
        }
        return usuarioRepository.saveAndFlush(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario usuario) {
        Optional<Usuario> emailExistente = usuarioRepository.findByEmail(usuario.getEmail());
        Usuario usuarioExistente = buscar(id);
        if (emailExistente.isPresent() && !emailExistente.get().equals(usuarioExistente)) {
            throw new NegocioException(String.format("Já existe um usuário cadastrado com o e-email %s", usuario.getEmail()));
        }
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());

        return usuarioRepository.saveAndFlush(usuarioExistente);
    }

    @Transactional
    public void remover(Long id) {
        try {
            Usuario usuarioExistente = buscar(id);
            usuarioRepository.delete(usuarioExistente);
            usuarioRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            log.error("usuario não encontrado", e);
            throw new UsuarioNaoEncontradoException(id);
        }
    }

    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscar(usuarioId);
        if (!usuario.senhaCoincideCom(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        usuario.setSenha(novaSenha);
        usuarioRepository.saveAndFlush(usuario);
    }

}
