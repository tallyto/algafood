package com.algaworks.algafood.notificacao;

import com.algaworks.algafood.modelo.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}
