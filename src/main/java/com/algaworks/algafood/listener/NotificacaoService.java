package com.algaworks.algafood.listener;

import com.algaworks.algafood.notificacao.NivelUrgencia;
import com.algaworks.algafood.notificacao.Notificador;
import com.algaworks.algafood.notificacao.TipoDoNotificador;
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
    public void clienteAtivadoListener (ClienteAtivadoEvent event){
//        System.out.println("Cliente " + event.getCliente().getNome() + " agora esta ativo");
        notificador.notificar(event.getCliente(), "Seu cadastro no sistema esta ativo");
    }
}
