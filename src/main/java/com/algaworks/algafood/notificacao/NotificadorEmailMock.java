package com.algaworks.algafood.notificacao;

import com.algaworks.algafood.modelo.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
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
