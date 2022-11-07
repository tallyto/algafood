package com.algaworks.algafood.jpa.pessoa;

import com.algaworks.algafood.AlgafoodApplication;
import com.algaworks.algafood.domain.model.Pessoa;
import com.algaworks.algafood.domain.repository.PessoaRepository;
import com.algaworks.algafood.infrastructure.repository.PessoaRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;



public class InclusaoPessoaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
                .web(WebApplicationType.NONE).run(args);

        PessoaRepository cadastroPessoa = applicationContext.getBean(PessoaRepository.class);
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Gustavo");
        pessoa.setIdade(21);
        pessoa = cadastroPessoa.salvar(pessoa);

        System.out.printf("%d - %s - %d\n", pessoa.getId(), pessoa.getNome(), pessoa.getIdade());

    }
}
