package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.model.DTO.EstadoDTO;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.api.openapi.controller.EstadoControllerOpenApi;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<EstadoDTO> listar() {
        return assembler.toCollectionDTO(cadastroEstado.listar());
    }

    @PostMapping
   public EstadoDTO adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = cadastroEstado.salvar(assembler.toEntity(estadoInput));
        return assembler.toDTO(estado);
    }

    @GetMapping("/{estadoId}")
    public EstadoDTO buscar(@PathVariable Long estadoId) {
        return assembler.toDTO(cadastroEstado.buscar(estadoId));
    }

    @PutMapping("/{estadoId}")
    public EstadoDTO atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estado) {
        Estado estadoAtulizado = cadastroEstado.atualizar(estadoId, assembler.toEntity(estado));
        return assembler.toDTO(estadoAtulizado);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        cadastroEstado.excluir(estadoId);
    }
}
