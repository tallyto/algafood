package com.tallyto.algafood.domain.exception;

import java.io.Serial;

public class UsuarioEmUsoException extends EntidadeEmUsoException {
    @Serial
    private static final long serialVersionUID = 1L;

    public UsuarioEmUsoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioEmUsoException(Long usuarioId) {
        this(String.format("Usuário de código %d não pode ser removido, pois está em uso", usuarioId));
    }
}
