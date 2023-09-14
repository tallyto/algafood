package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CidadeModelAssembler;
import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.DTO.CidadeDTO;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cidades")
@RestController
@RequestMapping("cidades")
public class CidadeController {
    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @ApiOperation(value = "Lista todas as cidades")
    @GetMapping
    public List<CidadeDTO> listar() {
        return cidadeModelAssembler.toCollectionDTO(cadastroCidade.listar());
    }

    @ApiOperation(value = "Busca uma cidade por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@ApiParam(value = "ID da cidade", example = "1") @PathVariable Long cidadeId) {
        return cidadeModelAssembler.toDTO(cadastroCidade.buscarOuFalhar(cidadeId));
    }

    @ApiOperation(value = "Cadastra uma nova cidade")
    @PostMapping
    public CidadeDTO adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true)
                               @RequestBody @Valid CidadeInput cidadeInput) {
        var cidade = cidadeModelAssembler.toEntity(cidadeInput);
        return cidadeModelAssembler.toDTO(cadastroCidade.salvar(cidade));
    }

    @ApiOperation(value = "Atualiza uma cidade por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Cidade atualizada com sucesso"),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@ApiParam(value = "ID da cidade", example = "1")
                               @PathVariable Long cidadeId,
                               @ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados",
                                   required = true) @RequestBody @Valid CidadeInput cidadeInput) {
        var cidade = cidadeModelAssembler.toEntity(cidadeInput);
        return cidadeModelAssembler.toDTO(cadastroCidade.atualizar(cidadeId, cidade));
    }

    @ApiOperation(value = "Exclui uma cidade por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Cidade excluída com sucesso"),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@ApiParam(value = "ID da cidade", example = "1")
                        @PathVariable Long cidadeId) {
        cadastroCidade.remover(cidadeId);
    }
}
