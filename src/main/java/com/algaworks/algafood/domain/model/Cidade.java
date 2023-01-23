package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.Groups;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.ConvertGroup;

@Getter
@Setter
@Entity
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Valid()
    @ConvertGroup(to = Groups.EstadoId.class)
    @ManyToOne()
    @JoinColumn(name = "estado_id", nullable = false)
    Estado estado;
}
