package com.example.demo.entity.conta;

import org.springframework.data.relational.core.mapping.Table;

import com.example.demo.entity.Cliente;

@Table(name = "CONTA")
public class Conta extends Cliente {
    private double saldo;
    private double limiteNegativo;
    private int tipoContaId;

    public Conta() {
        //TODO Auto-generated constructor stub
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
            " id='" + getId() + "'" +
            ", saldo='" + getSaldo() + "'" +
            ", limiteNegativo='" + getLimiteNegativo() + "'" +
            ", tipoContaId='" + getTipoContaId() + "'" +
            "}";
    }
}
