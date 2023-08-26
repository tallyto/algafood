package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.model.DTO.CozinhaDTO;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<CozinhaDTO> listar(@PageableDefault() Pageable pageable) {
        Page<Cozinha> cozinhaPage = cozinhaRepository.findAll(pageable);
        List<CozinhaDTO> cozinhaDTOs = cozinhaModelAssembler.toCollectionDTO(cozinhaPage.getContent());
        return new PageImpl<>(cozinhaDTOs, pageable, cozinhaPage.getTotalElements());
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
