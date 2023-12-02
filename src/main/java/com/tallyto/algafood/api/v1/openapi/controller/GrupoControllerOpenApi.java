package com.tallyto.algafood.api.v1.openapi.controller;

import com.tallyto.algafood.api.exceptionhandler.Problem;
import com.tallyto.algafood.api.v1.model.GrupoModel;
import com.tallyto.algafood.api.v1.model.input.GrupoInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.PathVariable;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {
    @ApiOperation("Lista grupos")
    CollectionModel<GrupoModel> listar();

    @ApiOperation("Busca grupo por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    GrupoModel buscar(@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId);

    @ApiOperation("Cadastra um novo grupo")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Grupo cadastrado com sucesso"),
        @ApiResponse(code = 400, message = "Dados do grupo inválidos", response = Problem.class)
    })
    GrupoModel criar(@ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true)
                     GrupoInput grupoInput);

    @ApiOperation("Atualiza um grupo por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Grupo atualizado com sucesso"),
        @ApiResponse(code = 400, message = "Dados do grupo inválidos", response = Problem.class),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    GrupoModel atualizar(@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId,
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
