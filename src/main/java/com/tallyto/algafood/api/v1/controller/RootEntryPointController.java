package com.tallyto.algafood.api.v1.controller;

import com.tallyto.algafood.api.v1.LinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private LinkBuilder linkBuilder;

    @GetMapping
    public RootEntryPointModel root() {
        var model = new RootEntryPointModel();

        model.add(linkBuilder.linkToCozinhas("cozinhas"));
        model.add(linkBuilder.linkToPedidos("pedidos"));
        model.add(linkBuilder.linkToRestaurantes("restaurantes"));
        model.add(linkBuilder.linkToUsuarios("usuarios"));
        model.add(linkBuilder.linkToGrupos("grupos"));
        model.add(linkBuilder.linkToCidades("cidades"));
        model.add(linkBuilder.linkToEstados("estados"));
        model.add(linkBuilder.linkToFormasPagamento("formas-pagamento"));
        model.add(linkBuilder.linkToEstatisticas("estatisticas"));

        return model;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
    }


}
