package com.example.demo.entity.conta;

public class Conta {
    private int id;
    private double saldo;
    private double limiteNegativo;
    private int tipoContaId; // 1: poupança, 2: corrente

    public Conta() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimiteNegativo() {
        return this.limiteNegativo;
    }

    public void setLimiteNegativo(double limiteNegativo) {
        this.limiteNegativo = limiteNegativo;
    }

    public int getTipoContaId() {
        return this.tipoContaId;
    }

    public void setTipoContaId(int tipoContaId) {
        this.tipoContaId = tipoContaId;
    }

    @Override
    public String toString() {
        return "{" +
            " saldo='" + getSaldo() + "'" +
            ", limiteNegativo='" + getLimiteNegativo() + "'" +
            ", tipoContaId='" + getTipoContaId() + "'" +
            "}";
    }
}
