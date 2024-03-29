package com.tallyto.algafood.api.v1.openapi.model;

import com.tallyto.algafood.api.v1.model.EstadoModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;
@ApiModel("EstadosModel")
@Data
public class EstadosModelOpenApi {

    private EstadosEmbeddedModelOpenApi _embedded;

    private Links _links;
    @ApiModel("EstadosEmbeddedModel")
    @Data
    public static class EstadosEmbeddedModelOpenApi {
        private List<EstadoModel> estados;
    }
}
