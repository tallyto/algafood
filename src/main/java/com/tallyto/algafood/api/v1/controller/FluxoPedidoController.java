package com.tallyto.algafood.api.v1.controller;

import com.tallyto.algafood.api.v1.openapi.controller.FluxoPedidoControllerOpenApi;
import com.tallyto.algafood.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pedidos/{codidoPedido}")
public class FluxoPedidoController implements FluxoPedidoControllerOpenApi {
    @Autowired
    private FluxoPedidoService pedidoService;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confirmar(@PathVariable String codidoPedido) {
        pedidoService.confirmar(codidoPedido);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancelar(@PathVariable String codidoPedido) {
        pedidoService.cancelar(codidoPedido);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/entregue")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> entregar(@PathVariable String codidoPedido) {
        pedidoService.entregue(codidoPedido);
        return ResponseEntity.noContent().build();
    }
}
