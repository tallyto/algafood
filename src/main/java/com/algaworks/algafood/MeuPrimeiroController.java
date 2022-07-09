package com.algaworks.algafood;

import com.algaworks.algafood.modelo.Cliente;
import com.algaworks.algafood.service.AtivacaoCliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {
    private AtivacaoCliente ativacaoCliente;

    public MeuPrimeiroController(AtivacaoCliente ativacaoCliente) {
        this.ativacaoCliente = ativacaoCliente;
        System.out.println("Meu primeiro controller" + ativacaoCliente);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        Cliente joao = new Cliente("João", "joao@xyz.com", "3333333");
        ativacaoCliente.ativar(joao);

        return  "Hello!";
    }
}
