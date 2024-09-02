package com.example.demo.entity;

public class Cliente {
    private int id;
    private String fatorRisco;
    private float rendaMensal;
    private int pessoaId;

    public Cliente(int id, String fatorRisco, float rendaMensal, int pessoaId) {
        this.id = id;
        this.fatorRisco = fatorRisco;
        this.rendaMensal = rendaMensal;
        this.pessoaId = pessoaId;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFatorRisco() {
        return this.fatorRisco;
    }

    public void setFatorRisco(String fatorRisco) {
        this.fatorRisco = fatorRisco;
    }

    public float getRendaMensal() {
        return this.rendaMensal;
    }

    public void setRendaMensal(float rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public int getPessoaId() {
        return this.pessoaId;
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", fatorRisco='" + getFatorRisco() + "'" +
            ", rendaMensal='" + getRendaMensal() + "'" +
            ", pessoaId='" + getPessoaId() + "'" +
            "}";
    }
}
