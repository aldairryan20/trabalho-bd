package com.example.demo.entity.conta;
import java.util.HashMap;

import org.springframework.boot.context.properties.bind.DefaultValue;

public class Conta {
    private int id;
    private double saldo;
    private double limiteNegativo;
    private int clienteId;
    private int tipoContaId;

    public Conta(int id, double saldo, @DefaultValue("-1000.0") double limiteNegativo) {
        this.id = id;
        this.saldo = saldo;
        this.limiteNegativo = limiteNegativo;
        this.clienteId = id;
        this.tipoContaId = id;
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

    public int getClienteId() {
        return this.clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getTipoContaId() {
        return this.tipoContaId;
    }

    public void setTipoContaId(int tipoContaId) {
        this.tipoContaId = tipoContaId;
    }

    public String getNome(int id, HashMap<Integer, String> hashMap) {
        return hashMap.get(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", saldo='" + getSaldo() + "'" +
            ", limiteNegativo='" + getLimiteNegativo() + "'" +
            ", clienteId='" + getClienteId() + "'" +
            ", tipoContaId='" + getTipoContaId() + "'" +
            "}";
    }
}
