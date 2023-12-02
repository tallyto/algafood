package com.tallyto.algafood.domain.exception;


import java.io.Serial;

public class CozinhaEmUsoException extends EntidadeEmUsoException {
    @Serial
    private static final long serialVersionUID = 1L;


    public CozinhaEmUsoException(String mensagem) {
        super(mensagem);
    }

    public CozinhaEmUsoException(Long cozinhaId) {
        this(String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
    }
}
