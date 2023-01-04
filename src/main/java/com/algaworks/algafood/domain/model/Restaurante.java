package com.algaworks.algafood.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

//    @JsonIgnore // não serializa o campo cozinha na resposta
    @JsonIgnoreProperties("hibernateLazyInitializer") // ignora o campo hibernateLazyInitializer
    @ManyToOne(fetch = FetchType.LAZY) // lazy = carregamento preguiçoso
    @JoinColumn(name = "cozinha_id", nullable = false)
    Cozinha cozinha;

    @JsonIgnore
    @Embedded // endereço é um tipo embutido
    private Endereco endereco;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "data_atualizacao", nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;

//    @JsonIgnore // não serializa o campo
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
        joinColumns = @JoinColumn(name = "restaurante_id"),
        inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();
}
