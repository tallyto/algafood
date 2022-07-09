package com.algaworks.algafood.notificacao;

import com.algaworks.algafood.modelo.Cliente;
import org.springframework.stereotype.Component;


public class NotificadorEmail implements Notificador {
    private boolean caixaAlta = false;
    private String hostServidorSmtp;

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }

    public NotificadorEmail(String hostServidorSmtp) {
        this.hostServidorSmtp = hostServidorSmtp;
        System.out.println("Notificador email");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        if (this.caixaAlta) {
            mensagem = mensagem.toUpperCase();
        }
        System.out.printf("Notificando %s atraves do email %s usando SMTP %s: %s\n", cliente.getNome(),
                cliente.getEmail(), this.hostServidorSmtp, mensagem);
    }
}
