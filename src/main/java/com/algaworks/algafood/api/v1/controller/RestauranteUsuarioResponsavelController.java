package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.LinkBuilder;
import com.algaworks.algafood.api.v1.assembler.UsuarioAssembler;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {
    @Autowired
    RestauranteService restauranteService;

    @Autowired
    UsuarioAssembler assembler;

    @Autowired
    LinkBuilder linkBuilder;

    @GetMapping
    public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscar(restauranteId);

        CollectionModel<UsuarioModel> usuariosModel = assembler
            .toCollectionModel(restaurante.getUsuarios())
            .removeLinks()
            .add(linkBuilder.linkToRestauranteResponsaveis(restauranteId))
            .add(linkBuilder.linkToRestauranteResponsavelAssociacao(restauranteId, "associar"));

        usuariosModel.getContent().forEach(usuarioModel -> {
            usuarioModel.add(linkBuilder.linkToRestauranteResponsavelDesassociacao(
                restauranteId, usuarioModel.getId(), "desassociar"));
        });

        return usuariosModel;
    }

    @PutMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.associarUsuarioResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.desassociarUsuarioResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

}
