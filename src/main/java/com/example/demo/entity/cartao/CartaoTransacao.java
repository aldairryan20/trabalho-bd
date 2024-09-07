package com.example.demo.entity.cartao;

public class CartaoTransacao {
    private int id;
    private String numCartao;
    private String cvc;
    private int cartaoCreditoId;
    private String tipoCartao;
    private String nomeCartao;
    private String tipoTransacao;
    private boolean isInternacional;
    private int bandeiraCartao;

    public CartaoTransacao() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumCartao() {
        return this.numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    public String getCvc() {
        return this.cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public int getCartaoCreditoId() {
        return this.cartaoCreditoId;
    }

    public void setCartaoCreditoId(int cartaoId) {
        this.cartaoCreditoId = cartaoId;
    }

    public String getTipoCartao() {
        return this.tipoCartao;
    }

    public void setTipoCartao(String tipoCartao) {
        this.tipoCartao = tipoCartao;
    }

    public String getNomeCartao() {
        return this.nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public String getTipoTransacao() {
        return this.tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public boolean getIsInternacional() {
        return this.isInternacional;
    }

    public void setIsInternacional(boolean isInternacional) {
        this.isInternacional = isInternacional;
    }

    public int getBandeiraCartao() {
        return this.bandeiraCartao;
    }

    public void setBandeiraCartao(int bandeiraCartao) {
        this.bandeiraCartao = bandeiraCartao;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", numCartao='" + getNumCartao() + "'" +
            ", cvc='" + getCvc() + "'" +
            ", cartaoId='" + getCartaoCreditoId() + "'" +
            ", tipoCartao='" + getTipoCartao() + "'" +
            ", nomeCartao='" + getNomeCartao() + "'" +
            ", tipoTransacao='" + getTipoTransacao() + "'" +
            ", isInternacional='" + getIsInternacional() + "'" +
            ", bandeiraCartao='" + getBandeiraCartao() + "'" +
            "}";
    }
}
