package com.example.demo.entity;
import java.util.Objects;

import org.springframework.data.annotation.Id;

public class Cliente {
    @Id
    private int id;
    private String fatorRisco;
    private float rendaMensal;
    private int pessoaId;

    public Cliente() {
    }

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

    public Cliente id(int id) {
        setId(id);
        return this;
    }

    public Cliente fatorRisco(String fatorRisco) {
        setFatorRisco(fatorRisco);
        return this;
    }

    public Cliente rendaMensal(float rendaMensal) {
        setRendaMensal(rendaMensal);
        return this;
    }

    public Cliente pessoaId(int pessoaId) {
        setPessoaId(pessoaId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cliente)) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        return id == cliente.id && Objects.equals(fatorRisco, cliente.fatorRisco) && rendaMensal == cliente.rendaMensal && pessoaId == cliente.pessoaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fatorRisco, rendaMensal, pessoaId);
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
