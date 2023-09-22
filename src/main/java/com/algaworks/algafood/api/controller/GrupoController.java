package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.GrupoAssembler;
import com.algaworks.algafood.api.openapi.controller.GrupoControllerOpenApi;
import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.DTO.GrupoDTO;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.GrupoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoAssembler assembler;

    @ApiOperation("Lista grupos")
    @GetMapping()
    public List<GrupoDTO> listar() {
        return assembler.toCollectionDTO(grupoService.listar());
    }

    @ApiOperation("Busca grupo por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    @GetMapping("{grupoId}")
    public GrupoDTO buscar(@ApiParam(value = "ID do grupo", example = "1", required = true) @PathVariable Long grupoId) {
        return assembler.toDTO(grupoService.buscar(grupoId));
    }

    @ApiOperation("Cadastra um novo grupo")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Grupo cadastrado com sucesso"),
        @ApiResponse(code = 400, message = "Dados do grupo inválidos", response = Problem.class)
    })
    @PostMapping()
    public GrupoDTO criar(@ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true)
                          @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoService.criar(assembler.toEntity(grupoInput));
        return assembler.toDTO(grupo);
    }


    @ApiOperation("Atualiza um grupo por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Grupo atualizado com sucesso"),
        @ApiResponse(code = 400, message = "Dados do grupo inválidos", response = Problem.class),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    @PutMapping("{grupoId}")
    public GrupoDTO atualizar(@ApiParam(value = "ID do grupo", example = "1", required = true) @PathVariable Long grupoId,
                              @ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados", required = true)
                              @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtualizado = grupoService.atualizar(grupoId, assembler.toEntity(grupoInput));
        return assembler.toDTO(grupoAtualizado);
    }

    @ApiOperation("Exclui um grupo por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Grupo excluído com sucesso"),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    @DeleteMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        grupoService.remover(grupoId);
    }


}
