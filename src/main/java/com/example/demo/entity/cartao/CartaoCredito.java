package com.example.demo.entity.cartao;

import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

import com.example.demo.dao.CartaoCreditoDAO;

public class CartaoCredito {
    private int id;
    private Date dataFechamento;
    private int contaId;
    private int catCartaoId;
    private double limiteCredito;

    private static CategoriaCartao categoria;

    public static CartaoCredito gerarCartao(int contaId, double limite) {
        var cartao = new CartaoCredito();
        cartao.setId(gerarId());

        var calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.MONTH, 1);
        cartao.setDataFechamento(new Date(calendar.getTimeInMillis()));

        cartao.setContaId(contaId);

        categoria = new CategoriaCartao();
        categoria.setId(cartao.getId());
        categoria.setDescricao("descricao");
        cartao.setCatCartaoId(categoria.getId());
        
        cartao.setLimiteCredito(limite);
        return cartao;
    }

    private static int gerarId() {
        var random = new Random().nextInt(Integer.MAX_VALUE);
        var ids = CartaoCreditoDAO.ids;
        if (ids.contains(random)) {
            return gerarId();
        }
        return random;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataFechamento() {
        return this.dataFechamento;
    }

    public void setDataFechamento(Date date) {
        this.dataFechamento = date;
    }

    public int getContaId() {
        return this.contaId;
    }

    public void setContaId(int contaId) {
        this.contaId = contaId;
    }

    public int getCatCartaoId() {
        return this.catCartaoId;
    }

    public void setCatCartaoId(int catCartaoId) {
        this.catCartaoId = catCartaoId;
    }

    public double getLimiteCredito() {
        return this.limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        if (limiteCredito < 0) {
            System.out.println("Compra recusada - Limite atingido");
            System.exit(-1);
        }
        this.limiteCredito = limiteCredito;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", dataFechamento='" + getDataFechamento() + "'" +
            ", contaId='" + getContaId() + "'" +
            ", catCartaoId='" + getCatCartaoId() + "'" +
            ", limiteCredito='" + getLimiteCredito() + "'" +
            "}";
    }
}
