package com.algaworks.algafood;

import com.algaworks.algafood.notificacao.NotificadorEmail;
import com.algaworks.algafood.service.AtivacaoCliente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration // serve para definicao dos beans
public class AlgaConfig {
    @Bean // configura um notificador email com hostSmtp e caixa alta ativo
    public NotificadorEmail notificadorEmail() {
        NotificadorEmail notificadorEmail = new NotificadorEmail("smtp.algamail.com.br");
        notificadorEmail.setCaixaAlta(true);
        return notificadorEmail;
    }

    @Bean
    public AtivacaoCliente ativacaoCliente() {
        return new AtivacaoCliente(notificadorEmail());
    }
}
