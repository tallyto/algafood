package com.algaworks.algafood.notificacao;

import com.algaworks.algafood.modelo.Cliente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
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
