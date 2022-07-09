package com.algaworks.algafood.notificacao;

import com.algaworks.algafood.modelo.Cliente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
@Qualifier("email") // qualificar o component
@Component
public class NotificadorEmail implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {

        System.out.printf("Notificando %s atraves do email %s: %s\n", cliente.getNome(),
                cliente.getEmail(), mensagem);
    }
}
