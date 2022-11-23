package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import javax.persistence.*;

@JsonRootName("cozinha")
@Data
@Entity // entidade do banco de dados
public class Cozinha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
}
