package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.UsuarioAssembler;
import com.algaworks.algafood.api.model.DTO.UsuarioDTO;
import com.algaworks.algafood.api.model.input.SenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.api.model.input.UsuarioWithoutPasswordInput;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.algaworks.algafood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioAssembler assembler;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<UsuarioDTO> listar() {
        return assembler.toCollectionDTO(usuarioService.listar());
    }

    @GetMapping("{usuarioId}")
    public UsuarioDTO buscar(@PathVariable Long usuarioId){
        return assembler.toDTO(usuarioService.buscar(usuarioId));
    }

    @PostMapping()
    public UsuarioDTO criar(@RequestBody @Valid UsuarioInput usuarioInput){
        Usuario usuario = assembler.toEntity(usuarioInput);
        return assembler.toDTO(usuarioService.criar(usuario));
    }

    @PutMapping("{usuarioId}")
    public UsuarioDTO atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioWithoutPasswordInput usuarioInput) {
        Usuario usuario = assembler.toEntityUpdate(usuarioInput);
        return assembler.toDTO(usuarioService.atualizar(usuarioId,usuario));
    }

    @PutMapping("{usuarioId}/senha")
    public ResponseEntity<?> alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senhaInput) {
        usuarioService.alterarSenha(usuarioId, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long usuarioId) {
        usuarioService.remover(usuarioId);
    }

}
