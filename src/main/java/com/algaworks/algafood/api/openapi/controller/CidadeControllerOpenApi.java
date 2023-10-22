package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;


@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {
    @ApiOperation(value = "Lista todas as cidades")
    CollectionModel<CidadeModel> listar();

    @ApiOperation(value = "Busca uma cidade por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    CidadeModel buscar(@ApiParam(value = "ID da cidade", example = "1", required = true) Long cidadeId);

    @ApiOperation(value = "Cadastra uma nova cidade")
    CidadeModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true)
                        CidadeInput cidadeInput);

    @ApiOperation(value = "Atualiza uma cidade por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Cidade atualizada com sucesso"),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    CidadeModel atualizar(@ApiParam(value = "ID da cidade", example = "1", required = true) Long cidadeId,
                          @ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados",
                            required = true) CidadeInput cidadeInput);

    @ApiOperation(value = "Exclui uma cidade por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Cidade excluída com sucesso"),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID da cidade", example = "1", required = true) Long cidadeId);
}
