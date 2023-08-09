package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.model.DTO.CozinhaDTO;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @GetMapping
    public List<CozinhaDTO> listar() {
        return cozinhaModelAssembler.toCollectionDTO(cozinhaRepository.findAll());
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaDTO buscar(@PathVariable Long cozinhaId) {
        return cozinhaModelAssembler.toDTO(cadastroCozinhaService.buscarOuFalhar(cozinhaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDTO adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        var cozinha = cozinhaModelAssembler.toEntity(cozinhaInput);
        return cozinhaModelAssembler.toDTO(cadastroCozinhaService.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaDTO atualizar(@PathVariable long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
        var cozinha = cozinhaModelAssembler.toEntity(cozinhaInput);
        return cozinhaModelAssembler.toDTO(cadastroCozinhaService.atualizar(cozinhaId, cozinha));
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable long cozinhaId) {
        cadastroCozinhaService.excluir(cozinhaId);
    }

}
