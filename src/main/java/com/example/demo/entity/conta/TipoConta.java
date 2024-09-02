package com.example.demo.entity.conta;

import java.util.Objects;

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

    public TipoConta id(int id) {
        setId(id);
        return this;
    }

    public TipoConta descricao(String descricao) {
        setDescricao(descricao);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TipoConta)) {
            return false;
        }
        TipoConta tipoConta = (TipoConta) o;
        return id == tipoConta.id && Objects.equals(descricao, tipoConta.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
