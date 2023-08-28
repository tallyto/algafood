package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("grupos/{grupoId}/permissoes")
public class GrupoPermissoesController {
    @Autowired
    private GrupoService grupoService;

    @GetMapping
    public Collection<Permissao> listar(@PathVariable Long grupoId) {
        return grupoService.buscar(grupoId).getPermissoes();
    }

    @DeleteMapping("{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.desassociarPermissao(grupoId, permissaoId);
    }

    @PutMapping("{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.associarPermissao(grupoId, permissaoId);
    }


}
