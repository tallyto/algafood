package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.GrupoAssembler;
import com.algaworks.algafood.api.model.DTO.GrupoDTO;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    GrupoAssembler assembler;

    @GetMapping
    public Collection<GrupoDTO> listar(@PathVariable Long usuarioId) {
        Collection<Grupo> grupos = usuarioService.buscar(usuarioId).getGrupos();

        return assembler.toCollectionDTO(grupos);
    }

    @PutMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.associarGrupo(usuarioId, grupoId);
    }

    @DeleteMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.desassociarGrupo(usuarioId, grupoId);
    }


}
