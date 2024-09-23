package com.example.demo.entity.cartao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;


import org.springframework.stereotype.Component;

import com.example.demo.interfaces.EntityFactory;

@Component
public class CartaoCreditoFactory implements EntityFactory<CartaoCredito> {
    @Override
    public CartaoCredito createFromResultSet(ResultSet rs) throws SQLException {
        var cartaoCredito = new CartaoCredito();

        int id = rs.getInt("id");
        Date dataFechamento = rs.getDate("dt_fechamento");
        int contaId = rs.getInt("conta_id");
        int catCartaoId = rs.getInt("categoria_cartao_id");
        double limiteCredito = rs.getDouble("limite_credito");

        cartaoCredito.setId(id);
        cartaoCredito.setDataFechamento(dataFechamento);
        cartaoCredito.setContaId(contaId);
        cartaoCredito.setCatCartaoId(catCartaoId);
        cartaoCredito.setLimiteCredito(limiteCredito);
        
        return cartaoCredito;
    }

}

