package com.example.demo.entity.pessoa.cliente;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.dao.BoletoDAO;
import com.example.demo.entity.cartao.CartaoCredito;
import com.example.demo.entity.compra.Pagamento;
import com.example.demo.entity.compra.fatura.FaturaCartao;
import com.example.demo.entity.conta.Conta;
import com.example.demo.entity.pessoa.Pessoa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Cliente extends Pessoa {
    private String fatorRisco;
    private double rendaMensal;
    private CartaoCredito cartao;
    private Conta conta;
    private ArrayList<FaturaCartao> faturas;

    BoletoDAO boletoDAO;

    public void loadFaturas() {
        faturas = new ArrayList<FaturaCartao>();
        System.out.println("faturas disponiveis");
    }

    public Optional<Pagamento> pagarBoletoUsandoConta(String codigo_barras) {
        var saldo = conta.getSaldo();
        var boleto = boletoDAO.getBoleto(codigo_barras);
        var valor = boleto.getValor();

        if(saldo < valor) {
            System.out.println("Compra recusada");
            return Optional.empty();
        }

        var dtPagamento = new Date(System.currentTimeMillis());
        var pagamento = new Pagamento();

        pagamento.setValorTotal(valor);
        pagamento.setDataPagamento(dtPagamento);
        pagamento.setValorTotal(valor);
        pagamento.setValorParcial(valor);

        conta.setSaldo(saldo -= valor);

        return Optional.of(pagamento);
    }

    public Optional<Pagamento> comprarComCartao(double valor, double taxa) {
        var limiteCartao = getCartao().getLimiteCredito();
        var total = valor + valor * taxa;

        System.out.println(getNome() + " está comprando algo no valor de: R$" + total);
        if (limiteCartao < total){
            System.out.println("Compra recusada");
            return Optional.empty();
        }

        cartao.setLimiteCredito(limiteCartao - total);

        // Criar um novo pagamento
        var pagamento = new Pagamento();
        pagamento.setValorTotal(total);
        pagamento.setDataPagamento(new Date(System.currentTimeMillis()));
        
        pagamento.setFaturaCartaoId(getFaturaAtual().getId());

        // Adicionar o valor à fatura atual
        FaturaCartao faturaAtual = getFaturaAtual();
        faturaAtual.setValor(faturaAtual.getValor() + total);

        return Optional.of(pagamento);
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

    public void setPessoa(Pessoa pessoa) {
        setNome(pessoa.getNome());
        setCpf(pessoa.getCpf());
        setId(pessoa.getId());
    }
}
