package com.example.demo.entity.compra;
import java.util.Date;

public class Compra {
    private int id;
    private double valor;
    private int qtdParcelas;
    private double taxaParcelas;
    private String credor;
    private int corretorId;
    private int cartaoTransacaoId;
    private Date dataCompra;

    public Compra() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQtdParcelas() {
        return this.qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public double getTaxaParcelas() {
        return this.taxaParcelas;
    }

    public void setTaxaParcelas(double taxaParcelas) {
        this.taxaParcelas = taxaParcelas;
    }

    public String getCredor() {
        return this.credor;
    }

    public void setCredor(String credor) {
        this.credor = credor;
    }

    public int getCorretorId() {
        return this.corretorId;
    }

    public void setCorretorId(int corretorId) {
        this.corretorId = corretorId;
    }

    public int getCartaoTransacaoId() {
        return this.cartaoTransacaoId;
    }

    public void setCartaoTransacaoId(int cartaoTransacaoId) {
        this.cartaoTransacaoId = cartaoTransacaoId;
    }

    public Date getDataCompra() {
        return this.dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", valor='" + getValor() + "'" +
            ", qtdParcelas='" + getQtdParcelas() + "'" +
            ", taxaParcelas='" + getTaxaParcelas() + "'" +
            ", credor='" + getCredor() + "'" +
            ", corretorId='" + getCorretorId() + "'" +
            ", cartaoTransacaoId='" + getCartaoTransacaoId() + "'" +
            ", dataCompra='" + getDataCompra() + "'" +
            "}";
    }
}
