package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.DTO.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

public interface CozinhaControllerOpenApi {
    @ApiOperation(value = "Lista todas as cozinhas")
    Page<CozinhaModel> listar(Pageable pageable);

    @ApiOperation(value = "Busca uma cozinha por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Cozinha encontrada"),
        @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    public CozinhaModel buscar(@ApiParam(value = "ID da cozinha", example = "1", required = true)
                             @PathVariable Long cozinhaId);

    @ApiOperation(value = "Cadastra uma nova cozinha")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Cozinha cadastrada com sucesso"),
        @ApiResponse(code = 400, message = "Dados inválidos", response = Problem.class)
    })
    CozinhaModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true)
                         CozinhaInput cozinhaInput);

    @ApiOperation(value = "Busca uma cozinha por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Cozinha encontrada"),
        @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    CozinhaModel atualizar(@ApiParam(value = "ID da cozinha", example = "1", required = true) Long cozinhaId,
                           @ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados",
                             required = true) CozinhaInput cozinhaInput);

    @ApiOperation(value = "Exclui uma cozinha por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Cozinha excluída com sucesso"),
        @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID da cozinha", example = "1", required = true)
                 Long cozinhaId);
}
