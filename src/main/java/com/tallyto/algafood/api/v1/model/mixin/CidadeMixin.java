package com.tallyto.algafood.api.v1.model.mixin;

import com.tallyto.algafood.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CidadeMixin {
    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    Estado estado;
}
