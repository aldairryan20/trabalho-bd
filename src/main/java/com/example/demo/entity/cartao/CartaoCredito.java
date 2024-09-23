package com.example.demo.entity.cartao;

import java.sql.Date;

public class CartaoCredito {
    private int id;
    private Date dataFechamento;
    private int contaId;
    private int catCartaoId;
    private double limiteCredito;
    private BandeiraCartao bandeira;

    public BandeiraCartao getBandeira() {
        return this.bandeira;
    }

    public void setBandeira(BandeiraCartao bandeira) {
        this.bandeira = bandeira;
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

    public void setDataFechamento(Date date) {
        this.dataFechamento = date;
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
        if (limiteCredito < 0) {
            System.out.println("Compra recusada - Limite atingido");
            System.exit(-1);
        }
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
