package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.UsuarioAssembler;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {
    @Autowired
    RestauranteService restauranteService;

    @Autowired
    UsuarioAssembler assembler;

    @GetMapping
    public Collection<UsuarioModel> listar(@PathVariable Long restauranteId) {
        Collection<Usuario> usuario = restauranteService.buscar(restauranteId).getUsuarios();
        return assembler.toCollectionDTO(usuario);
    }

    @PutMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.associarUsuarioResponsavel(restauranteId, usuarioId);
    }

    @DeleteMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.desassociarUsuarioResponsavel(restauranteId, usuarioId);
    }

}
