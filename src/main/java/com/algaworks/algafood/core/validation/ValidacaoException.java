package com.algaworks.algafood.core.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException {
    private BindingResult bindingResult;
}
