package com.example.demo.entity.cartao;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CartaoCredito {
    private int id;
    private Date dataFechamento;
    private int contaId;
    private int catCartaoId;
    private double limiteCredito;
    private BandeiraCartao bandeira;
}
