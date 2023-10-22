package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.api.openapi.controller.EstadoControllerOpenApi;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController implements EstadoControllerOpenApi {
    @Autowired
    EstadoService cadastroEstado;

    @Autowired
    EstadoModelAssembler assembler;

    @GetMapping
    public CollectionModel<EstadoModel> listar() {
        return assembler.toCollectionModel(cadastroEstado.listar());
    }

    @PostMapping
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = cadastroEstado.salvar(assembler.toEntity(estadoInput));
        return assembler.toModel(estado);
    }

    @GetMapping("/{estadoId}")
    public EstadoModel buscar(@PathVariable Long estadoId) {
        return assembler.toModel(cadastroEstado.buscar(estadoId));
    }

    @PutMapping("/{estadoId}")
    public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estado) {
        Estado estadoAtulizado = cadastroEstado.atualizar(estadoId, assembler.toEntity(estado));
        return assembler.toModel(estadoAtulizado);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        cadastroEstado.excluir(estadoId);
    }
}
