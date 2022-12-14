package com.algaworks.algafood.jpa.restaurante;

import com.algaworks.algafood.AlgafoodApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;


public class InclusaoRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
                .web(WebApplicationType.NONE).run(args);

        RestauranteRepository cadastroRestaurante = applicationContext.getBean(RestauranteRepository.class);

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("Amor&Cana");
        restaurante1.setTaxaFrete(new BigDecimal("5.0"));
        restaurante1 = cadastroRestaurante.salvar(restaurante1);

        System.out.printf("%d - %s - %f\n", restaurante1.getId(), restaurante1.getNome(), restaurante1.getTaxaFrete());

    }
}
