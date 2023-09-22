package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.DTO.GrupoDTO;
import com.algaworks.algafood.api.model.input.GrupoInput;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {
    @ApiOperation("Lista grupos")
    List<GrupoDTO> listar();

    @ApiOperation("Busca grupo por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    GrupoDTO buscar(@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId);

    @ApiOperation("Cadastra um novo grupo")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Grupo cadastrado com sucesso"),
        @ApiResponse(code = 400, message = "Dados do grupo inválidos", response = Problem.class)
    })
    GrupoDTO criar(@ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true)
                   GrupoInput grupoInput);

    @ApiOperation("Atualiza um grupo por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Grupo atualizado com sucesso"),
        @ApiResponse(code = 400, message = "Dados do grupo inválidos", response = Problem.class),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    GrupoDTO atualizar(@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId,
                       @ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados", required = true)
                       GrupoInput grupoInput);

    @ApiOperation("Exclui um grupo por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Grupo excluído com sucesso"),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID do grupo", example = "1", required = true)
                 @PathVariable Long grupoId);

}
