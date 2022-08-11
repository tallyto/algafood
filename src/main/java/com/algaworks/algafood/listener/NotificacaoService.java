package com.algaworks.algafood.listener;

import com.algaworks.algafood.notification.NivelUrgencia;
import com.algaworks.algafood.notification.Notificador;
import com.algaworks.algafood.notification.TipoDoNotificador;
import com.algaworks.algafood.service.ClienteAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {
    @TipoDoNotificador(NivelUrgencia.URGENTE)
    @Autowired
    private Notificador notificador;

    @EventListener // metodo ouvinte de um evento
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        notificador.notificar(event.getCliente(), "Seu cadastro no sistema esta ativo");
    }
}
