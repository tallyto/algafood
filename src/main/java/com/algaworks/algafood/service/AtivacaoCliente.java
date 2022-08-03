package com.algaworks.algafood.service;

import com.algaworks.algafood.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

//@Component
public class AtivacaoCliente {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void ativar(Cliente cliente) {
        cliente.ativar();
        // fizer para o container que o cliente esta ativo neste momento
        eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
    }
}
