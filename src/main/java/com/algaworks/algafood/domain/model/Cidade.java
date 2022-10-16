package com.algaworks.algafood.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne()
    @JoinColumn(name = "estado_id", nullable = false)
    Estado estado;
}
