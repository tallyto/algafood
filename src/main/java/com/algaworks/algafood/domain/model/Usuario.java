package com.algaworks.algafood.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_grupo",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    @ToString.Exclude
    private Set<Grupo> grupos = new HashSet<>();

    public void associar(Grupo grupo) {
        grupos.add(grupo);
    }

    public void desassociar(Grupo grupo) {
        grupos.remove(grupo);
    }

    public boolean senhaCoincideCom(String senhaAtual) {
        return senha.equals(senhaAtual);
    }
}
