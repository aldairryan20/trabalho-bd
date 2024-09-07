package com.example.demo.entity.compra.fatura;
import java.util.Date;

public class FaturaCartao {
    private int id;
    private String mesRef;
    private String anoRef;
    private double valor;
    private Date dataPagamento;
    private int cartaoCreditoId;

    public FaturaCartao() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMesRef() {
        return this.mesRef;
    }

    public void setMesRef(String mesRef) {
        this.mesRef = mesRef;
    }

    public String getAnoRef() {
        return this.anoRef;
    }

    public void setAnoRef(String anoRef) {
        this.anoRef = anoRef;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataPagamento() {
        return this.dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getCartaoCreditoId() {
        return this.cartaoCreditoId;
    }

    public void setCartaoCreditoId(int cartaoCreditoId) {
        this.cartaoCreditoId = cartaoCreditoId;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", mesRef='" + getMesRef() + "'" +
            ", anoRef='" + getAnoRef() + "'" +
            ", valor='" + getValor() + "'" +
            ", dataPagamento='" + getDataPagamento() + "'" +
            ", cartaoCreditoId='" + getCartaoCreditoId() + "'" +
            "}";
    }
}
