package com.algaworks.algafood.domain.exception;

import java.io.Serial;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{
    @Serial
    private static final long serialVersionUID = 1L;

    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProdutoNaoEncontradoException(Long produtoId) {
        this(String.format("Não existe um produto com código %d", produtoId));
    }
}
