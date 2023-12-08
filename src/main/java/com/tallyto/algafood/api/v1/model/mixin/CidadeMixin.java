package com.tallyto.algafood.api.v1.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tallyto.algafood.domain.model.Estado;

public class CidadeMixin {
    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    Estado estado;
}
