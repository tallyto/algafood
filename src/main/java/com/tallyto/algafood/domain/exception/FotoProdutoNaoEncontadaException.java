package com.tallyto.algafood.domain.exception;

import java.io.Serial;

public class FotoProdutoNaoEncontadaException extends EntidadeNaoEncontradaException {
    @Serial
    private static final long serialVersionUID = 1L;

    public FotoProdutoNaoEncontadaException(String mensagem) {
        super(mensagem);
    }

    public FotoProdutoNaoEncontadaException(Long restauranteId, Long produtoId) {
        this(String.format("Não existe um cadastro de uma foto para o produto com código %d no restaurante de código %d",
            produtoId, restauranteId));
    }

    public FotoProdutoNaoEncontadaException(Long produtoId) {
        this(String.format("Não existe uma foto para o produto com código %d", produtoId));
    }
}
