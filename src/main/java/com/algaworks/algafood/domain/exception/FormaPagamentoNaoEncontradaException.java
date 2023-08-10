package com.algaworks.algafood.domain.exception;

import java.io.Serial;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {
    @Serial
    private static final long serialVersionUID = 1L;

    public FormaPagamentoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FormaPagamentoNaoEncontradaException(Long restauranteId) {
        this(String.format("Não existe um cadastro de forma de pagamento com código %d", restauranteId));
    }
}
