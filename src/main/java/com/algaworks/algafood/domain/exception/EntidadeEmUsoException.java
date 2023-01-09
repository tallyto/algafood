package com.algaworks.algafood.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serial;

public class EntidadeEmUsoException extends ResponseStatusException {
    @Serial
    private static final long serialVersionUID = 1L;

    public EntidadeEmUsoException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public EntidadeEmUsoException(String mensagem) {
        this(HttpStatus.CONFLICT, mensagem);
    }
}
