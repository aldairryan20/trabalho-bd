package com.example.demo.entity.conta;
import java.util.Objects;

import org.springframework.data.annotation.Id;

public class Conta {
    @Id
    private int id;
    private double saldo;
    private double limiteNegativo;
    private int clienteId;
    private int tipoContaId;

    public Conta() {
    }

    public Conta(int id, double saldo, double limiteNegativo, int clienteId, int tipoContaId) {
        this.id = id;
        this.saldo = saldo;
        this.limiteNegativo = limiteNegativo;
        this.clienteId = clienteId;
        this.tipoContaId = tipoContaId;
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

    public Conta id(int id) {
        setId(id);
        return this;
    }

    public Conta saldo(double saldo) {
        setSaldo(saldo);
        return this;
    }

    public Conta limiteNegativo(double limiteNegativo) {
        setLimiteNegativo(limiteNegativo);
        return this;
    }

    public Conta clienteId(int clienteId) {
        setClienteId(clienteId);
        return this;
    }

    public Conta tipoContaId(int tipoContaId) {
        setTipoContaId(tipoContaId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Conta)) {
            return false;
        }
        Conta conta = (Conta) o;
        return id == conta.id && saldo == conta.saldo && limiteNegativo == conta.limiteNegativo && clienteId == conta.clienteId && tipoContaId == conta.tipoContaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saldo, limiteNegativo, clienteId, tipoContaId);
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
