package com.algaworks.algafood.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "forma_pagamento")
public class FormaPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;
}
