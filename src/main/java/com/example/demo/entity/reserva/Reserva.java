package com.example.demo.entity.reserva;

import java.util.Date;

import org.springframework.data.annotation.Id;
import java.util.Objects;

public class Reserva {
    @Id
    private int id;
    private Date dataMovimentacao;
    private String tipoMovimentacao;
    private int contaId;

    public Reserva() {
    }

    public Reserva(int id, Date dataMovimentacao, String tipoMovimentacao, int contaId) {
        this.id = id;
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

    public Reserva id(int id) {
        setId(id);
        return this;
    }

    public Reserva dataMovimentacao(Date dataMovimentacao) {
        setDataMovimentacao(dataMovimentacao);
        return this;
    }

    public Reserva tipoMovimentacao(String tipoMovimentacao) {
        setTipoMovimentacao(tipoMovimentacao);
        return this;
    }

    public Reserva contaId(int contaId) {
        setContaId(contaId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Reserva)) {
            return false;
        }
        Reserva reserva = (Reserva) o;
        return id == reserva.id && Objects.equals(dataMovimentacao, reserva.dataMovimentacao) && Objects.equals(tipoMovimentacao, reserva.tipoMovimentacao) && contaId == reserva.contaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataMovimentacao, tipoMovimentacao, contaId);
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
