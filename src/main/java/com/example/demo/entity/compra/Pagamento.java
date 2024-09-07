package com.example.demo.entity.compra;
import java.util.Date;

public class Pagamento {
    private int id;
    private double valorTotal;
    private Date dataPagamento;
    private int faturaCartaoId;
    private double valorParcial;
    private int boletoId;

    public Pagamento() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorTotal() {
        return this.valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataPagamento() {
        return this.dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getFaturaCartaoId() {
        return this.faturaCartaoId;
    }

    public void setFaturaCartaoId(int faturaCartaoId) {
        this.faturaCartaoId = faturaCartaoId;
    }

    public double getValorParcial() {
        return this.valorParcial;
    }

    public void setValorParcial(double valorParcial) {
        this.valorParcial = valorParcial;
    }

    public int getBoletoId() {
        return this.boletoId;
    }

    public void setBoletoId(int boletoId) {
        this.boletoId = boletoId;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", valorTotal='" + getValorTotal() + "'" +
            ", dataPagamento='" + getDataPagamento() + "'" +
            ", faturaCartaoId='" + getFaturaCartaoId() + "'" +
            ", valorParcial='" + getValorParcial() + "'" +
            ", boletoId='" + getBoletoId() + "'" +
            "}";
    }
}
