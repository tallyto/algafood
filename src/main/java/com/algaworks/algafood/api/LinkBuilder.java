package com.algaworks.algafood.api;

import com.algaworks.algafood.api.controller.PedidoController;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class LinkBuilder {

    public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
        new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
        new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
        new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
    );
    public Link linkToPedidos() {

        TemplateVariables filtroVariables = new TemplateVariables(
            new TemplateVariable("clienteId", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("restauranteId", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("dataCriacaoInicio", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("dataCriacaoFim", TemplateVariable.VariableType.REQUEST_PARAM)
        );

        String pedidosUrl = linkTo(PedidoController.class).toUri().toString();

        return Link.of(UriTemplate.of(pedidosUrl, PAGINACAO_VARIABLES.concat(filtroVariables)), "pedidos");
    }
}
