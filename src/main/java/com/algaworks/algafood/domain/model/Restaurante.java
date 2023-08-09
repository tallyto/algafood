package com.algaworks.algafood.domain.model;


import com.algaworks.algafood.Groups;
import com.algaworks.algafood.core.validation.Multiplo;
import com.algaworks.algafood.core.validation.TaxaFrete;
import com.algaworks.algafood.core.validation.ValorZeroIncluiDescricao;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Restaurante {
    @Id
    // passa a responsabilidade de gerar a chave para o banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotNull
    @TaxaFrete
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @Valid // faz a validação dos campos da cozinha
    @ConvertGroup(to = Groups.CozinhaId.class)
    @ManyToOne // (fetch = FetchType.LAZY) lazy = carregamento preguiçoso
    @JoinColumn(name = "cozinha_id", nullable = false)
    Cozinha cozinha;

    @Embedded // endereço é um tipo embutido
    private Endereco endereco;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @Column(name = "data_atualizacao", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;

    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
        joinColumns = @JoinColumn(name = "restaurante_id"),
        inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();
}
