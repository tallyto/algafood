package com.algaworks.algafood.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

public class EstadoEmUsoException extends EntidadeEmUsoException {
    @Serial
    private static final long serialVersionUID = 1L;


    public EstadoEmUsoException(String mensagem) {
        super(mensagem);
    }

    public EstadoEmUsoException(Long estadoId) {
        this(String.format("Estado de código %d não pode ser removido, pois está em uso", estadoId));
    }
}
