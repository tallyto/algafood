package com.tallyto.algafood.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class FotoProduto {
    @Id
    @Column(name = "produto_id")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Produto produto;

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;


    public Long getRestauranteId() {
        if (getProduto() != null) {
            return getProduto().getRestaurante().getId();
        }
        return null;
    }


}
