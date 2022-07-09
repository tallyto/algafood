package com.algaworks.algafood.service;

import com.algaworks.algafood.modelo.Cliente;
import com.algaworks.algafood.notificacao.Notificador;
import com.algaworks.algafood.notificacao.NotificadorEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoCliente {

    private Notificador notificador;

    @Autowired // Define o construtor a ser usado
    public AtivacaoCliente(NotificadorEmail notificador) {
        this.notificador = notificador;
        System.out.println("Ativacao cliente" + notificador);
    }

    public void ativar(Cliente cliente) {
        cliente.ativar();
        notificador.notificar(cliente, "Seu cadastro no sistema esta ativo!");
    }
}
