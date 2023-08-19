package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.GrupoAssembler;
import com.algaworks.algafood.api.model.DTO.GrupoDTO;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoAssembler assembler;

    @GetMapping()
    public List<GrupoDTO> listar(){
       return assembler.toCollectionDTO(grupoService.listar());
    }

    @GetMapping("{grupoId}")
    public GrupoDTO buscar(@PathVariable Long grupoId){
        return assembler.toDTO(grupoService.buscar(grupoId));
    }

    @PostMapping()
    public GrupoDTO criar(@RequestBody @Valid GrupoInput grupoInput){
        Grupo grupo =  grupoService.criar(assembler.toEntity(grupoInput));
        return assembler.toDTO(grupo);
    }

    @PutMapping("{grupoId}")
    public GrupoDTO atualizar(@PathVariable Long grupoId, @RequestBody GrupoInput grupoInput){
        Grupo grupoAtualizado = grupoService.atualizar(grupoId, assembler.toEntity(grupoInput));
        return assembler.toDTO(grupoAtualizado);
    }

    @DeleteMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId){
        grupoService.remover(grupoId);
    }


}
