package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FormaPagamentoAssembler;
import com.algaworks.algafood.api.model.DTO.FormaPagamentoDTO;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequestMapping("/formas-pagamento")
@RestController
public class FormaPagamentoController {

    @Autowired
    FormaPagamentoService cadastroFormaPagamento;

    @Autowired
    FormaPagamentoAssembler assembler;

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoDTO>> listar(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
        String eTag = "0";

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getUltimaAtualizacao();

        if(dataUltimaAtualizacao != null ){
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

        if(request.checkNotModified(eTag)){
            return null;
        }

        var formasPagamentoModel = assembler.toCollectionDTO(cadastroFormaPagamento.listar());

        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
            .eTag(eTag)
//            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
//            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
//            .cacheControl(CacheControl.noCache())
//            .cacheControl(CacheControl.noStore())
            .body(formasPagamentoModel);
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoDTO> buscar(@PathVariable Long formaPagamentoId) {
        var formaPagamento = assembler.toDTO(cadastroFormaPagamento.buscar(formaPagamentoId));
        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
            .body(formaPagamento);
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
    public void remover(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamento.remover(formaPagamentoId);
    }


}
