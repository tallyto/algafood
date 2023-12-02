package com.tallyto.algafood.domain.exception;


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
