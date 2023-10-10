package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.GrupoAssembler;
import com.algaworks.algafood.api.model.DTO.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.api.openapi.controller.GrupoControllerOpenApi;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoAssembler assembler;

    @GetMapping()
    public List<GrupoModel> listar() {
        return assembler.toCollectionDTO(grupoService.listar());
    }

    @GetMapping("{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId) {
        return assembler.toDTO(grupoService.buscar(grupoId));
    }

    @PostMapping()
    public GrupoModel criar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoService.criar(assembler.toEntity(grupoInput));
        return assembler.toDTO(grupo);
    }

    @PutMapping("{grupoId}")
    public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtualizado = grupoService.atualizar(grupoId, assembler.toEntity(grupoInput));
        return assembler.toDTO(grupoAtualizado);
    }

    @DeleteMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        grupoService.remover(grupoId);
    }


}
