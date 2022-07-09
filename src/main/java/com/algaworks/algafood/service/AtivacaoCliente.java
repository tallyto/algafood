package com.algaworks.algafood.service;

import com.algaworks.algafood.modelo.Cliente;
import com.algaworks.algafood.notificacao.Notificador;
import com.algaworks.algafood.notificacao.NotificadorEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoCliente {

    @Autowired(required = false)
    private Notificador notificador;

    public void ativar(Cliente cliente) {
        cliente.ativar();
        if(notificador != null) {
            notificador.notificar(cliente, "Seu cadastro no sistema esta ativo!");
        } else {
            System.out.println("Nao existe notificador, mas cliente foi ativado");
        }
    }
}
