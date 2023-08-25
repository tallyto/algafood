package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{pedidoId}")
public class FluxoPedidoController {
    @Autowired
    private FluxoPedidoService pedidoService;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable Long pedidoId){
        pedidoService.confirmar(pedidoId);
    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long pedidoId){
        pedidoService.cancelar(pedidoId);
    }

    @PutMapping("/entregue")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregue(@PathVariable Long pedidoId){
        pedidoService.entregue(pedidoId);
    }
}
