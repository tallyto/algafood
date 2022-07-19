package com.algaworks.algafood;

import com.algaworks.algafood.service.AtivacaoCliente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public AtivacaoCliente ativacaoCliente() {
        return new AtivacaoCliente();
    }

}
