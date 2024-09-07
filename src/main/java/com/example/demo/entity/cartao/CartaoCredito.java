package com.example.demo.entity.cartao;
import java.util.Date;

public class CartaoCredito {
    private int id;
    private Date dataFechamento;
    private int contaId;
    private int catCartaoId;
    private double limiteCredito;

    public CartaoCredito() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataFechamento() {
        return this.dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public int getContaId() {
        return this.contaId;
    }

    public void setContaId(int contaId) {
        this.contaId = contaId;
    }

    public int getCatCartaoId() {
        return this.catCartaoId;
    }

    public void setCatCartaoId(int catCartaoId) {
        this.catCartaoId = catCartaoId;
    }

    public double getLimiteCredito() {
        return this.limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", dataFechamento='" + getDataFechamento() + "'" +
            ", contaId='" + getContaId() + "'" +
            ", catCartaoId='" + getCatCartaoId() + "'" +
            ", limiteCredito='" + getLimiteCredito() + "'" +
            "}";
    }
}
