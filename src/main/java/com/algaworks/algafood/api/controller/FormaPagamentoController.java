package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FormaPagamentoAssembler;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.api.openapi.controller.FormaPagamentoControllerOpenApi;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequestMapping(value = "/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

    @Autowired
    FormaPagamentoService cadastroFormaPagamento;

    @Autowired
    FormaPagamentoAssembler assembler;

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
        String eTag = "0";

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getUltimaAtualizacao();

        if (dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        var formasPagamentoModel = assembler.toCollectionDTO(cadastroFormaPagamento.listar());

        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
            .eTag(eTag)
            .body(formasPagamentoModel);
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId) {

        var formaPagamento = cadastroFormaPagamento.buscar(formaPagamentoId);
        var formaPagamentoDTO = assembler.toDTO(formaPagamento);

        return ResponseEntity.ok(formaPagamentoDTO);
    }

    @PostMapping
    public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        return assembler.toDTO(cadastroFormaPagamento.salvar(assembler.toEntity(formaPagamentoInput)));
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        return assembler.toDTO(cadastroFormaPagamento.atualizar(formaPagamentoId, assembler.toEntity(formaPagamentoInput)));
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamento.remover(formaPagamentoId);
    }


}
