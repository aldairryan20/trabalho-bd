package com.example.demo.entity.conta;

public class TipoConta {
    private int id;
    private String descricao;

    public TipoConta(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
