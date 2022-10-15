package com.algaworks.algafood.domain.model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // passa a responsabilidade de gerar a chave para o banco de dados
    private Long id;

    private String nome;

    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;
}
