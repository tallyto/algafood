package com.tallyto.algafood.api.v1.controller;

import com.tallyto.algafood.api.v1.assembler.UsuarioAssembler;
import com.tallyto.algafood.api.v1.model.UsuarioModel;
import com.tallyto.algafood.api.v1.model.input.SenhaInput;
import com.tallyto.algafood.api.v1.model.input.UsuarioInput;
import com.tallyto.algafood.api.v1.model.input.UsuarioWithoutPasswordInput;
import com.tallyto.algafood.api.v1.openapi.controller.UsuarioControllerOpenApi;
import com.tallyto.algafood.domain.model.Usuario;
import com.tallyto.algafood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioAssembler assembler;

    @GetMapping
    public CollectionModel<UsuarioModel> listar() {
        return assembler.toCollectionModel(usuarioService.listar());
    }

    @GetMapping("{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        return assembler.toModel(usuarioService.buscar(usuarioId));
    }

    @PostMapping()
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuario = assembler.toEntity(usuarioInput);
        return assembler.toModel(usuarioService.salvar(usuario));
    }

    @PutMapping("{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioWithoutPasswordInput usuarioInput) {
        Usuario usuario = assembler.toEntityUpdate(usuarioInput);
        return assembler.toModel(usuarioService.atualizar(usuarioId, usuario));
    }

    @Override
    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }


    @DeleteMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long usuarioId) {
        usuarioService.remover(usuarioId);
    }

}
