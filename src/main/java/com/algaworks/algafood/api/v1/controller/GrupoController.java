package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.input.GrupoInput;
import com.algaworks.algafood.api.v1.openapi.controller.GrupoControllerOpenApi;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoModelAssembler assembler;

    @GetMapping()
    public CollectionModel<GrupoModel> listar() {
        return assembler.toCollectionModel(grupoService.listar());
    }

    @GetMapping("{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId) {
        return assembler.toModel(grupoService.buscar(grupoId));
    }

    @PostMapping()
    public GrupoModel criar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoService.criar(assembler.toEntity(grupoInput));
        return assembler.toModel(grupo);
    }

    @PutMapping("{grupoId}")
    public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtualizado = grupoService.atualizar(grupoId, assembler.toEntity(grupoInput));
        return assembler.toModel(grupoAtualizado);
    }

    @DeleteMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        grupoService.remover(grupoId);
    }


}
