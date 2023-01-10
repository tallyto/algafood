package com.algaworks.algafood.api.exceptionhandler;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice // dentro do pacote exceptionhandler, temos uma classe que vai ser responsável por tratar as exceções de forma global
public class ApiExceptionHandler extends ResponseEntityExceptionHandler { // ResponseEntityExceptionHandler é uma classe do Spring que já tem alguns tratamentos de exceções

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
        Problema problema = Problema.builder()
            .dataHora(LocalDateTime.now())
            .mensagem(e.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);

    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
