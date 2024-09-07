package com.example.demo.entity.compra.fatura;

public class ItensFatura {
    private int id;
    private String descricao;
    private int faturaCartaoId;

    public ItensFatura() {
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

    public int getFaturaCartaoId() {
        return this.faturaCartaoId;
    }

    public void setFaturaCartaoId(int faturaCartaoId) {
        this.faturaCartaoId = faturaCartaoId;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", faturaCartaoId='" + getFaturaCartaoId() + "'" +
            "}";
    }
}
