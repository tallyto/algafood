package com.algaworks.algafood.api.model.input;

import com.algaworks.algafood.domain.model.Cozinha;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CozinhaIdInput {
    @NotNull
    private Long id;

    public Cozinha toModel() {
        Cozinha cozinha = new Cozinha();
        cozinha.setId(id);
        return cozinha;
    }
}
