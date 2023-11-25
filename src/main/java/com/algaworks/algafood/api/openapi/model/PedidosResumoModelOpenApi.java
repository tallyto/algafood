package com.algaworks.algafood.api.openapi.model;

import com.algaworks.algafood.api.model.PedidoResumoModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("PedidosResumoModel")
public class PedidosResumoModelOpenApi extends PagedModelOpenApi<PedidoResumoModel> {
    private PedidosResumoEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @ApiModel("PedidosResumoEmbeddedModel")
    @Data
    public class PedidosResumoEmbeddedModelOpenApi {

        private List<PedidoResumoModel> pedidos;

    }
}
