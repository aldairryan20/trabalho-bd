package com.example.demo.entity.conta;
import com.example.demo.entity.cartao.CartaoCredito;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Conta {
    private int id;
    private double saldo;
    private double limiteNegativo;
    private int tipoContaId;

    private CartaoCredito cartao;
    
    void pagar(double valor) {
        if(saldo < valor) {
            System.out.println("Saldo insuficiente");
            System.exit(-1);
        }
        saldo -= valor;
    }
    
    void receber(double valor) {
        if(valor < 0) {
            System.exit(-1);
        }
        saldo += valor;
    }
}
