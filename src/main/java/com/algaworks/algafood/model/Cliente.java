package com.algaworks.algafood.model;

import lombok.Getter;

@Getter
public class Cliente {
    private String nome;
    private String email;

    private String telefone;

    private boolean ativo = false;

    public Cliente(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void ativar() {
        this.setAtivo(true);
    }
}
