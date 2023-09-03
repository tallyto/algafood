package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FluxoPedidoService {
    @Autowired
    private EmissaoPedidoService pedidoService;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EnvioEmailService envioEmailService;

    @Transactional
    public void confirmar(String codigoPedido) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
        pedido.confirmar();
        var mensagem = EnvioEmailService.Mensagem.builder()
            .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
            .corpo("pedido-confirmado.ftl")
            .variavel("pedido", pedido)
            .destinatario(pedido.getCliente().getEmail())
            .build();
        envioEmailService.enviar(mensagem);
        pedidoRepository.saveAndFlush(pedido);
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
        pedido.cancelar();
        pedidoRepository.saveAndFlush(pedido);
    }

    @Transactional
    public void entregue(String codigoPedido) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
        pedido.entregar();
        pedidoRepository.saveAndFlush(pedido);
    }


}
