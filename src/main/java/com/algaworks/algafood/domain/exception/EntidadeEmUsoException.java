package com.algaworks.algafood.domain.exception;


import java.io.Serial;

public abstract class EntidadeEmUsoException extends NegocioException {
    @Serial
    private static final long serialVersionUID = 1L;


    public EntidadeEmUsoException(String mensagem) {
        super(mensagem);
    }
}
