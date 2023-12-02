package com.tallyto.algafood.api.v1.controller;

import com.tallyto.algafood.api.v1.LinkBuilder;
import com.tallyto.algafood.api.v1.assembler.FormaPagamentoAssembler;
import com.tallyto.algafood.api.v1.model.FormaPagamentoModel;
import com.tallyto.algafood.api.v1.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import com.tallyto.algafood.domain.model.Restaurante;
import com.tallyto.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {
    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private FormaPagamentoAssembler assembler;

    @Autowired
    private LinkBuilder linkBuilder;

    @GetMapping
    public CollectionModel<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscar(restauranteId);

        var formaPagamentoModel = assembler.toCollectionModel(restaurante.getFormasPagamento())
            .removeLinks()
            .add(linkBuilder.linkToRestauranteFormasPagamento(restauranteId))
            .add(linkBuilder.linkToRestauranteFormaPagamentoAssociacao(restauranteId, "associar"));

        formaPagamentoModel.getContent().forEach(formaPagamento -> {
            formaPagamento.add(linkBuilder.linkToRestauranteFormaPagamentoDesassociacao(restauranteId, formaPagamento.getId(), "desassociar"));
        });

        return formaPagamentoModel;
    }

    @Override
    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }
}
