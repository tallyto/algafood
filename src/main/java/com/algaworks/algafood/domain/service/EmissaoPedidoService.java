package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RestauranteService cadastroRestaurante;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private UsuarioService cadastroUsuario;

    @Autowired
    private ProdutoService cadastroProduto;

    @Autowired
    private FormaPagamentoService cadastroFormaPagamento;

    @Transactional
    public Pedido emitir(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }

    private void validarPedido(Pedido pedido) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
        Usuario cliente = cadastroUsuario.buscar(pedido.getCliente().getId());
        Restaurante restaurante = cadastroRestaurante.buscar(pedido.getRestaurante().getId());
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscar(pedido.getFormaPagamento().getId());

        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);

        if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
            throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                formaPagamento.getDescricao()));
        }
    }

    private void validarItens(Pedido pedido) {
        List<ItemPedido> novosItens = new ArrayList<>();

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = cadastroProduto.buscarOuFalhar(
                pedido.getRestaurante().getId(), item.getProduto().getId());

            ItemPedido novoItem = new ItemPedido();
            novoItem.setPedido(pedido);
            novoItem.setProduto(produto);
            novoItem.setPrecoUnitario(produto.getPreco());
            novoItem.setQuantidade(item.getQuantidade()); // Configura a quantidade do novo item
            novoItem.setObservacao(item.getObservacao()); // Configura a observação do novo item

            novosItens.add(novoItem);
        }

        pedido.getItens().clear(); // Limpa a lista de itens atual
        pedido.getItens().addAll(novosItens); // Adiciona os novos itens à lista do pedido
    }


    public Pedido buscarOuFalhar(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
    }

}
