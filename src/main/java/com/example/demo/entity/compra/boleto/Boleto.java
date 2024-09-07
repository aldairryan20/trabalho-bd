package com.example.demo.entity.compra.boleto;
import java.util.Date;

public class Boleto {
    private int id;
    private double valor;
    private Date dataVencimento;
    private Date dataGeracao;
    private String codigoBarras;
    private int tipoBoletoId;
    private int faturaCartaoId;

    public Boleto() {
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

    public Date getDataVencimento() {
        return this.dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataGeracao() {
        return this.dataGeracao;
    }

    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getCodigoBarras() {
        return this.codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public int getTipoBoletoId() {
        return this.tipoBoletoId;
    }

    public void setTipoBoletoId(int tipoBoletoId) {
        this.tipoBoletoId = tipoBoletoId;
    }

    public int getFaturaCartaoId() {
        return this.faturaCartaoId;
    }

    public void setFaturaCartaoId(int faturaCartaoId) {
        this.faturaCartaoId = faturaCartaoId;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", valor='" + getValor() + "'" +
            ", dataVencimento='" + getDataVencimento() + "'" +
            ", dataGeracao='" + getDataGeracao() + "'" +
            ", codigoBarras='" + getCodigoBarras() + "'" +
            ", tipoBoletoId='" + getTipoBoletoId() + "'" +
            ", faturaCartaoId='" + getFaturaCartaoId() + "'" +
            "}";
    }
}
