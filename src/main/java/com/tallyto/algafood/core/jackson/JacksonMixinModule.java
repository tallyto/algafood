package com.tallyto.algafood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.tallyto.algafood.api.v1.model.mixin.CidadeMixin;
import com.tallyto.algafood.api.v1.model.mixin.RestauranteMixin;
import com.tallyto.algafood.domain.model.Cidade;
import com.tallyto.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
    }
}
