package com.algaworks.algafood.api.exceptionhandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
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
    public ResponseEntity<Object> tratarNegocioException(NegocioException ex, WebRequest request) {
        Problema problema = Problema.builder()
            .dataHora(LocalDateTime.now())
            .mensagem(ex.getMessage())
            .build();
        return handleExceptionInternal(ex, problema, new HttpHeaders(),HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<Object> tratarEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
        Problema problema = Problema.builder()
            .dataHora(LocalDateTime.now())
            .mensagem(ex.getMessage())
            .build();
        return handleExceptionInternal(ex, problema, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override // sobrescrevendo o método handleExceptionInternal para mudar o corpo da resposta
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(status.getReasonPhrase())
                .build();
        } else if (body instanceof String) {
            body = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem((String) body)
                .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

}
