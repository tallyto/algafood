package com.tallyto.algafood.domain.exception;

import java.io.Serial;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {
    @Serial
    private static final long serialVersionUID = 1L;

    public GrupoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public GrupoNaoEncontradoException(Long grupoId) {
        this(String.format("Não existe um grupo com código %d", grupoId));
    }
}
