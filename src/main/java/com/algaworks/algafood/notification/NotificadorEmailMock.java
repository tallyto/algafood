package com.algaworks.algafood.notification;

import com.algaworks.algafood.model.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("development")
@TipoDoNotificador(value = NivelUrgencia.URGENTE)
@Component
public class NotificadorEmailMock implements Notificador {

    public NotificadorEmailMock() {
        System.out.println("Notificador email MOCK");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {

        System.out.printf("MOCK: Notificando seria enviada para %s atraves do email %s: %s\n", cliente.getNome(),
                cliente.getEmail(), mensagem);
    }
}
