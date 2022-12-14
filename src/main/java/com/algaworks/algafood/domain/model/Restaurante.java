package com.algaworks.algafood.domain.model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // passa a responsabilidade de gerar a chave para o banco de dados
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @ManyToOne
//    @OnDelete(action = OnDeleteAction.CASCADE) // Remove o restaurante caso a cozinha seja apagada
    @JoinColumn(name = "cozinha_id", nullable = false)
    Cozinha cozinha;
}
