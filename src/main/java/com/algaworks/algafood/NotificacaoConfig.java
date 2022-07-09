package com.algaworks.algafood;

import com.algaworks.algafood.notificacao.NotificadorEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificacaoConfig {
    @Bean // configura um notificador email com hostSmtp e caixa alta ativo
    public NotificadorEmail notificadorEmail() {
        NotificadorEmail notificadorEmail = new NotificadorEmail("smtp.algamail.com.br");
        notificadorEmail.setCaixaAlta(true);
        return notificadorEmail;
    }

}
