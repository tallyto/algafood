package com.algaworks.algafood.domain.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity // entidade do banco de dados
public class Cozinha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cozinha cozinha = (Cozinha) o;
        return id != null && Objects.equals(id, cozinha.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
