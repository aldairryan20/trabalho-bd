package com.example.demo.entity.movimentacao;

import java.util.Date;

import org.springframework.data.annotation.Id;
import java.util.Objects;

public class MovimentacaoConta {
    @Id
    private int id;
    private double valor;
    private Date dataMovimentacao;
    private String tipoMovimentacao;
    private int contaId;

    public MovimentacaoConta() {
    }

    public MovimentacaoConta(int id, double valor, Date dataMovimentacao, String tipoMovimentacao, int contaId) {
        this.id = id;
        this.valor = valor;
        this.dataMovimentacao = dataMovimentacao;
        this.tipoMovimentacao = tipoMovimentacao;
        this.contaId = contaId;
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

    public Date getDataMovimentacao() {
        return this.dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getTipoMovimentacao() {
        return this.tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public int getContaId() {
        return this.contaId;
    }

    public void setContaId(int contaId) {
        this.contaId = contaId;
    }

    public MovimentacaoConta id(int id) {
        setId(id);
        return this;
    }

    public MovimentacaoConta valor(double valor) {
        setValor(valor);
        return this;
    }

    public MovimentacaoConta dataMovimentacao(Date dataMovimentacao) {
        setDataMovimentacao(dataMovimentacao);
        return this;
    }

    public MovimentacaoConta tipoMovimentacao(String tipoMovimentacao) {
        setTipoMovimentacao(tipoMovimentacao);
        return this;
    }

    public MovimentacaoConta contaId(int contaId) {
        setContaId(contaId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MovimentacaoConta)) {
            return false;
        }
        MovimentacaoConta movimentacaoConta = (MovimentacaoConta) o;
        return id == movimentacaoConta.id && valor == movimentacaoConta.valor && Objects.equals(dataMovimentacao, movimentacaoConta.dataMovimentacao) && Objects.equals(tipoMovimentacao, movimentacaoConta.tipoMovimentacao) && contaId == movimentacaoConta.contaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, dataMovimentacao, tipoMovimentacao, contaId);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", valor='" + getValor() + "'" +
            ", dataMovimentacao='" + getDataMovimentacao() + "'" +
            ", tipoMovimentacao='" + getTipoMovimentacao() + "'" +
            ", contaId='" + getContaId() + "'" +
            "}";
    }
}
