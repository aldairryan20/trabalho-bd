package com.example.demo.entity;

public class Cliente {
    private int id;
    private String fatorRisco;
    private double rendaMensal;

    public Cliente() {
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

    public double getRendaMensal() {
        return this.rendaMensal;
    }

    public void setRendaMensal(double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", fatorRisco='" + getFatorRisco() + "'" +
            ", rendaMensal='" + getRendaMensal() + "'" +
            "}";
    }
}
