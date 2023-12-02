package com.tallyto.algafood.api.v1.controller;

import com.tallyto.algafood.api.v1.LinkBuilder;
import com.tallyto.algafood.api.v1.assembler.PermissaoModelAssembler;
import com.tallyto.algafood.api.v1.model.PermissaoModel;
import com.tallyto.algafood.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.tallyto.algafood.domain.model.Grupo;
import com.tallyto.algafood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/grupos/{grupoId}/permissoes")
public class GrupoPermissoesController implements GrupoPermissaoControllerOpenApi {
    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @Autowired
    private LinkBuilder linkBuilder;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscar(grupoId);

        CollectionModel<PermissaoModel> permissoesModel
            = permissaoModelAssembler.toCollectionModel(grupo.getPermissoes())
            .removeLinks()
            .add(linkBuilder.linkToGrupoPermissoes(grupoId))
            .add(linkBuilder.linkToGrupoPermissaoAssociacao(grupoId, "associar"));

        permissoesModel.getContent().forEach(permissaoModel -> {
            permissaoModel.add(linkBuilder.linkToGrupoPermissaoDesassociacao(
                grupoId, permissaoModel.getId(), "desassociar"));
        });

        return permissoesModel;
    }


    @Override
    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.desassociarPermissao(grupoId, permissaoId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.associarPermissao(grupoId, permissaoId);

        return ResponseEntity.noContent().build();
    }


}
