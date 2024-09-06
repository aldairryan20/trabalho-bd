package com.example.demo.entity;
import java.util.Objects;

import com.example.demo.entity.pessoa.Pessoa;

public class Cliente extends Pessoa {
    private String fatorRisco;
    private float rendaMensal;

    public Cliente(int id, String fatorRisco, float rendaMensal) {
        super();
        this.id = id;
        this.fatorRisco = fatorRisco;
        this.rendaMensal = rendaMensal;
    }

    public Cliente() {
        //TODO Auto-generated constructor stub
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

    @Override
    public int hashCode() {
        return Objects.hash(id, fatorRisco, rendaMensal);
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
