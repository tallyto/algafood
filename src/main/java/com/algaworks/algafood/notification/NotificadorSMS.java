package com.algaworks.algafood.notification;

import com.algaworks.algafood.model.Cliente;
import org.springframework.stereotype.Component;

//@Primary
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorSMS implements Notificador {
    @Override
    public void notificar(Cliente cliente, String mensagem) {

        System.out.printf("Notificando %s por SMS atraves do telefone %s: %s\n", cliente.getNome(),
                cliente.getTelefone(), mensagem);
    }
}
