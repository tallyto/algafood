package com.algaworks.algafood.domain.exception;

public class GrupoEmUsoException extends EntidadeEmUsoException {
    public GrupoEmUsoException(String mensagem) {
        super(mensagem);
    }

    public GrupoEmUsoException(Long grupoID) {
        this(String.format("Grupo de código %d não pode ser removido, pois está em uso", grupoID));
    }
}
