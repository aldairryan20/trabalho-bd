package com.example.demo.entity.movimentacao;
import java.util.Date;

public class MovimentacaoReserva {
    private int id;
    private Date dataMovimentacao;
    private double valor;
    private String tipoMovimentacao;
    private int reservaId;

    public MovimentacaoReserva() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataMovimentacao() {
        return this.dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipoMovimentacao() {
        return this.tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public int getReservaId() {
        return this.reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", dataMovimentacao='" + getDataMovimentacao() + "'" +
            ", valor='" + getValor() + "'" +
            ", tipoMovimentacao='" + getTipoMovimentacao() + "'" +
            ", reservaId='" + getReservaId() + "'" +
            "}";
    }
}
