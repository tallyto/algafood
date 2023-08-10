package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FormaPagamentoAssembler;
import com.algaworks.algafood.api.model.DTO.FormaPagamentoDTO;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.exception.CozinhaEmUsoException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/formas-pagamento")
@RestController
public class FormaPagamentoController {

    @Autowired
    FormaPagamentoService cadastroFormaPagamento;

    @Autowired
    FormaPagamentoAssembler assembler;

    @GetMapping
    public List<FormaPagamentoDTO> listar() {
        return assembler.toCollectionDTO(cadastroFormaPagamento.listar());
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoDTO buscar(@PathVariable Long formaPagamentoId) {
        return assembler.toDTO(cadastroFormaPagamento.buscar(formaPagamentoId));
    }

    @PostMapping
    public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        return assembler.toDTO(cadastroFormaPagamento.salvar(assembler.toEntity(formaPagamentoInput)));
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoDTO atualizar(@PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        return assembler.toDTO(cadastroFormaPagamento.atualizar(formaPagamentoId, assembler.toEntity(formaPagamentoInput)));
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable  Long formaPagamentoId) {
        cadastroFormaPagamento.remover(formaPagamentoId);
    }


}
