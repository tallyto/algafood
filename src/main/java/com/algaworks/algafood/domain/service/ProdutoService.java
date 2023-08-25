package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto buscar(Long produtoId) {
        return produtoRepository.findById(produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
    }

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId)
            .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }

    @Transactional
    public Produto adicionar(Produto produto) {
        return produtoRepository.save(produto);
    }


    @Transactional
    public Produto atualizar(Long produtoId, Produto produto) {
        Produto produtoAtual = buscar(produtoId);
        produtoAtual.setNome(produto.getNome());
        produtoAtual.setDescricao(produto.getDescricao());
        produtoAtual.setPreco(produto.getPreco());
        produtoAtual.setAtivo(produto.isAtivo());
        produtoAtual.setRestaurante(produto.getRestaurante());
        return produtoRepository.saveAndFlush(produtoAtual);
    }

    @Transactional
    public void remover(Long produtoId) {
        Produto produto = buscar(produtoId);
        produtoRepository.delete(produto);
        produtoRepository.flush();
    }
}
