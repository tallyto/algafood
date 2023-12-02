package com.tallyto.algafood.api.v1.controller;

import com.tallyto.algafood.api.v1.assembler.EstadoModelAssembler;
import com.tallyto.algafood.api.v1.model.EstadoModel;
import com.tallyto.algafood.api.v1.model.input.EstadoInput;
import com.tallyto.algafood.api.v1.openapi.controller.EstadoControllerOpenApi;
import com.tallyto.algafood.domain.model.Estado;
import com.tallyto.algafood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/estados")
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
