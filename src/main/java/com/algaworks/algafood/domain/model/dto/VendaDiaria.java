package com.algaworks.algafood.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
@Setter
public class VendaDiaria {
    private OffsetDateTime data;
    private Long totalVenda;
    private BigDecimal totalFaturado;
}
