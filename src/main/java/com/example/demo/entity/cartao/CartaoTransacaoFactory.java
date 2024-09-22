package com.example.demo.entity.cartao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.example.demo.interfaces.EntityFactory;

@Component
public class CartaoTransacaoFactory implements EntityFactory<CartaoTransacao> {
    @Override
    public CartaoTransacao createFromResultSet(ResultSet rs) throws SQLException {
        var cartaoTransacao = new CartaoTransacao();

        int id = rs.getInt("id");
        String numCartao = rs.getString("num_cartao");
        String cvc = rs.getString("cvc");
        int cartaoCreditoId = rs.getInt("cartao_credito_id");
        String tipoCartao = rs.getString("tipo_cartao");
        String nomeCartao = rs.getString("nome_cartao");
        String tipoTransacao = rs.getString("tipo_transacao");
        boolean isInternacional = rs.getBoolean("is_internacional");
        int bandeiraCartao = rs.getInt("bandeira_cartao");

        cartaoTransacao.setId(id);
        cartaoTransacao.setNumCartao(numCartao);
        cartaoTransacao.setCvc(cvc);
        cartaoTransacao.setCartaoCreditoId(cartaoCreditoId);
        cartaoTransacao.setTipoCartao(tipoCartao);
        cartaoTransacao.setNomeCartao(nomeCartao);
        cartaoTransacao.setTipoTransacao(tipoTransacao);
        cartaoTransacao.setIsInternacional(isInternacional);
        cartaoTransacao.setBandeiraCartao(bandeiraCartao);
        
        return cartaoTransacao;
    }
}
