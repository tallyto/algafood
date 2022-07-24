package com.algaworks.algafood.notificacao;

import com.algaworks.algafood.modelo.Cliente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@TipoDoNotificador(value = NivelUrgencia.URGENTE)
@Component
public class NotificadorEmail implements Notificador {
    @Value("${notificador.email.host-servidor}")
    private String host;

    @Value("${notificador.email.porta-servidor}")

    private Integer porta;
    public NotificadorEmail() {
        System.out.println("Notificador email REAL");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Host: " + host);
        System.out.println("Porta: " + porta);
        System.out.printf("Notificando %s atraves do email %s: %s\n", cliente.getNome(),
                cliente.getEmail(), mensagem);
    }
}
