package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.UsuarioAssembler;
import com.algaworks.algafood.api.model.DTO.UsuarioDTO;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteResponsavelController {
    @Autowired
    RestauranteService restauranteService;

    @Autowired
    UsuarioAssembler assembler;

    @GetMapping
    public Collection<UsuarioDTO> listar(@PathVariable Long restauranteId){
        Collection<Usuario> usuario = restauranteService.buscar(restauranteId).getUsuarios();
        return assembler.toCollectionDTO(usuario);
    }

    @PutMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarUsuarioResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId){
        restauranteService.associarUsuarioResponsavel(restauranteId, usuarioId);
    }

    @DeleteMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarUsuarioResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId){
        restauranteService.desassociarUsuarioResponsavel(restauranteId, usuarioId);
    }

}