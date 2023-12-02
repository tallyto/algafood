package com.tallyto.algafood.domain.exception;

import java.io.Serial;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {
    @Serial
    private static final long serialVersionUID = 1L;

    public PermissaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public PermissaoNaoEncontradaException(Long permissaoId) {
        this(String.format("Não existe uma permissão com código %d", permissaoId));
    }
}
