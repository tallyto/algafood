package com.tallyto.algafood.api.v1.controller;

import com.tallyto.algafood.api.utils.ResourceUriHelper;
import com.tallyto.algafood.api.v1.assembler.CidadeModelAssembler;
import com.tallyto.algafood.api.v1.model.CidadeModel;
import com.tallyto.algafood.api.v1.model.input.CidadeInput;
import com.tallyto.algafood.api.v1.openapi.controller.CidadeControllerOpenApi;
import com.tallyto.algafood.domain.model.Cidade;
import com.tallyto.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {
    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Deprecated
    @GetMapping
    public CollectionModel<CidadeModel> listar() {
        List<Cidade> cidades = cadastroCidade.listar();
        return cidadeModelAssembler.toCollectionModel(cidades);
    }

    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

        return cidadeModelAssembler.toModel(cidade);
    }

    @PostMapping
    public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
        var cidade = cidadeModelAssembler.toEntity(cidadeInput);

        cidade = cadastroCidade.salvar(cidade);

        CidadeModel cidadeDTO = cidadeModelAssembler.toModel(cidade);

        ResourceUriHelper.addUriInResponseHeader(cidadeDTO.getId());

        return cidadeDTO;
    }

    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {
        var cidade = cidadeModelAssembler.toEntity(cidadeInput);
        return cidadeModelAssembler.toModel(cadastroCidade.atualizar(cidadeId, cidade));
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidade.remover(cidadeId);
    }
}
