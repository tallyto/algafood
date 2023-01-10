package com.algaworks.algafood.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class CidadeEmUsoException extends EntidadeEmUsoException {
    @Serial
    private static final long serialVersionUID = 1L;


    public CidadeEmUsoException(String mensagem) {
        super(mensagem);
    }

    public CidadeEmUsoException(Long cidadeId) {
        this(String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
    }
}
