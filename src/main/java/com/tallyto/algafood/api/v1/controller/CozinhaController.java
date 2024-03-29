package com.tallyto.algafood.api.v1.controller;

import com.tallyto.algafood.api.v1.assembler.CozinhaModelAssembler;
import com.tallyto.algafood.api.v1.model.CozinhaModel;
import com.tallyto.algafood.api.v1.model.input.CozinhaInput;
import com.tallyto.algafood.api.v1.openapi.controller.CozinhaControllerOpenApi;
import com.tallyto.algafood.domain.model.Cozinha;
import com.tallyto.algafood.domain.repository.CozinhaRepository;
import com.tallyto.algafood.domain.service.CadastroCozinhaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j

@RestController
@RequestMapping(value = "/v1/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<CozinhaModel> listar(@PageableDefault(size = 10) Pageable pageable) {
        log.info("Consultando cozinhas com páginas de {} registros...", pageable.getPageSize());
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

        return pagedResourcesAssembler
            .toModel(cozinhasPage, cozinhaModelAssembler);
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable Long cozinhaId) {
        return cozinhaModelAssembler.toModel(cadastroCozinhaService.buscarOuFalhar(cozinhaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        var cozinha = cozinhaModelAssembler.toEntity(cozinhaInput);
        return cozinhaModelAssembler.toModel(cadastroCozinhaService.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
        var cozinha = cozinhaModelAssembler.toEntity(cozinhaInput);
        return cozinhaModelAssembler.toModel(cadastroCozinhaService.atualizar(cozinhaId, cozinha));
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.excluir(cozinhaId);
    }

}
