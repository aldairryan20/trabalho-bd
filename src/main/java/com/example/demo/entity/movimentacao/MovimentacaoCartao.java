package com.example.demo.entity.movimentacao;
import java.util.Date;

public class MovimentacaoCartao {
    private int id;
    private Date dataMovimentacao;
    private String tipoMovimentacao;
    private int contaId;

    public MovimentacaoCartao() {
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

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", dataMovimentacao='" + getDataMovimentacao() + "'" +
            ", tipoMovimentacao='" + getTipoMovimentacao() + "'" +
            ", contaId='" + getContaId() + "'" +
            "}";
    }
}