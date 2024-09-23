package com.example.demo.entity.pessoa.cliente;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.dao.BoletoDAO;
import com.example.demo.entity.cartao.CartaoCredito;
import com.example.demo.entity.compra.Pagamento;
import com.example.demo.entity.compra.fatura.FaturaCartao;
import com.example.demo.entity.conta.Conta;
import com.example.demo.entity.pessoa.Pessoa;

public class Cliente extends Pessoa {
    private String fatorRisco;
    private double rendaMensal;
    private CartaoCredito cartao;
    private Conta conta;
    private ArrayList<FaturaCartao> faturas;

    public void loadFaturas() {
        faturas = new ArrayList<FaturaCartao>();
        System.out.println("faturas disponiveis");
    }

    public Conta getConta() {
        return this.conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
    

    public CartaoCredito getCartao() {
        return this.cartao;
    }

    public void setCartao(CartaoCredito cartao) {
        this.cartao = cartao;
    }

    public String getFatorRisco() {
        return this.fatorRisco;
    }

    public void setFatorRisco(String fatorRisco) {
        this.fatorRisco = fatorRisco;
    }

    public double getRendaMensal() {
        return this.rendaMensal;
    }

    public void setRendaMensal(double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public List<FaturaCartao> getFaturas() {
        return faturas;
    }

    public void setFaturas(ArrayList<FaturaCartao> faturas) {
        this.faturas = faturas;
    }

    public Pagamento pagarBoleto(BoletoDAO boletoDAO, String codigo_barras) {
        var boleto = boletoDAO.getBoleto(codigo_barras);
        var pagamento = new Pagamento();

        pagamento.setValorTotal(boleto.getValor());
        pagamento.setDataPagamento(new Date(System.currentTimeMillis()));
        pagamento.setFaturaCartaoId(getFaturaAtual().getId());
        cartao.setLimiteCredito(cartao.getLimiteCredito() - boleto.getValor());

        return pagamento;
    }

    public Pagamento comprar(double valor) {
        System.out.println(getNome() + " está comprando algo no valor de: R$" + valor);
        var limite = cartao.getLimiteCredito();
        cartao.setLimiteCredito(limite - valor);

        // Criar um novo pagamento
        Pagamento pagamento = new Pagamento();
        pagamento.setValorTotal(valor);
        pagamento.setDataPagamento(new Date(System.currentTimeMillis()));
        pagamento.setFaturaCartaoId(getFaturaAtual().getId());

        // Adicionar o valor à fatura atual
        FaturaCartao faturaAtual = getFaturaAtual();
        faturaAtual.setValor(faturaAtual.getValor() + valor);

        return pagamento;
    }

    public FaturaCartao getFaturaAtual() {
        if (faturas.isEmpty() || faturaExpirada(faturas.get(faturas.size() - 1))) {
            FaturaCartao novaFatura = new FaturaCartao();
            novaFatura.setId(faturas.size() + 1); // Simples incremento de ID
            novaFatura.setMesRef(String.valueOf(new Date(System.currentTimeMillis()).toLocalDate().getMonthValue()));
            novaFatura.setAnoRef(String.valueOf(new Date(System.currentTimeMillis()).toLocalDate().getYear()));
            novaFatura.setCartaoCreditoId(cartao.getId());
            faturas.add(novaFatura);
        }
        return faturas.get(faturas.size() - 1);
    }

    private boolean faturaExpirada(FaturaCartao fatura) {
        // Verifica se a fatura é do mês atual
        Date hoje = new Date(System.currentTimeMillis());
        return !(fatura.getMesRef().equals(String.valueOf(hoje.toLocalDate().getMonthValue())) &&
                 fatura.getAnoRef().equals(String.valueOf(hoje.toLocalDate().getYear())));
    }

    public double verificarFaturaAtual() {
        return getFaturaAtual().getValor();
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", fatorRisco='" + getFatorRisco() + "'" +
            ", rendaMensal='" + getRendaMensal() + "'" +
            "}";
    }
}
