package com.tallyto.algafood.domain.service;

import com.tallyto.algafood.domain.exception.NegocioException;
import com.tallyto.algafood.domain.exception.UsuarioEmUsoException;
import com.tallyto.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.tallyto.algafood.domain.model.Grupo;
import com.tallyto.algafood.domain.model.Usuario;
import com.tallyto.algafood.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    GrupoService grupoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {

        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
            throw new NegocioException(
                String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
        }

        if (usuario.isNovo()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        return usuarioRepository.save(usuario);
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
            throw new UsuarioNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new UsuarioEmUsoException(id);
        }
    }

    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscar(usuarioId);

        if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(passwordEncoder.encode(novaSenha));
    }

    @Transactional
    public void associarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscar(usuarioId);
        Grupo grupo = grupoService.buscar(grupoId);
        usuario.associar(grupo);
        usuarioRepository.saveAndFlush(usuario);

    }

    @Transactional
    public void desassociarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscar(usuarioId);
        Grupo grupo = grupoService.buscar(grupoId);
        usuario.desassociar(grupo);
        usuarioRepository.saveAndFlush(usuario);
    }
}
