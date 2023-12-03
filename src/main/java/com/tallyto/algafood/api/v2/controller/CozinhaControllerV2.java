package com.tallyto.algafood.api.v2.controller;


import com.tallyto.algafood.api.v2.assembler.CozinhaModelAssemblerV2;
import com.tallyto.algafood.api.v2.model.CozinhaModelV2;
import com.tallyto.algafood.api.v2.model.input.CozinhaInputV2;
import com.tallyto.algafood.domain.model.Cozinha;
import com.tallyto.algafood.domain.repository.CozinhaRepository;
import com.tallyto.algafood.domain.service.CadastroCozinhaService;
import io.swagger.annotations.Api;
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

@Api(tags = "Cozinhas")
@RestController
@RequestMapping(value = "/v2/cozinhas")
public class CozinhaControllerV2  {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private CozinhaModelAssemblerV2 cozinhaModelAssembler;

    @Autowired
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<CozinhaModelV2> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

        return pagedResourcesAssembler
            .toModel(cozinhasPage, cozinhaModelAssembler);
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModelV2 buscar(@PathVariable Long cozinhaId) {
        return cozinhaModelAssembler.toModel(cadastroCozinhaService.buscarOuFalhar(cozinhaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModelV2 adicionar(@RequestBody @Valid CozinhaInputV2 cozinhaInput) {
        var cozinha = cozinhaModelAssembler.toEntity(cozinhaInput);
        return cozinhaModelAssembler.toModel(cadastroCozinhaService.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaModelV2 atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInputV2 cozinhaInput) {
        var cozinha = cozinhaModelAssembler.toEntity(cozinhaInput);
        return cozinhaModelAssembler.toModel(cadastroCozinhaService.atualizar(cozinhaId, cozinha));
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.excluir(cozinhaId);
    }

}
