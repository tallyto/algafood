package com.algaworks.algafood.domain.listener;

import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class NotificacaoClientePedidoConfirmadoListener {

    @Autowired
    private EnvioEmailService envioEmailService;
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
        Pedido pedido = event.getPedido();
        log.info("Enviando e-mail de notificação do pedido confirmado: {}", pedido);
        var mensagem = EnvioEmailService.Mensagem.builder()
            .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
            .corpo("pedido-confirmado.ftl")
            .variavel("pedido", pedido)
            .destinatario(pedido.getCliente().getEmail())
            .build();
        envioEmailService.enviar(mensagem);
    }
}
