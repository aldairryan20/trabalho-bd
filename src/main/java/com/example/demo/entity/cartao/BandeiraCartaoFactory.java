package com.example.demo.entity.cartao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.example.demo.interfaces.EntityFactory;

@Component
public class BandeiraCartaoFactory implements EntityFactory<BandeiraCartao> {
    @Override
    public BandeiraCartao createFromResultSet(ResultSet rs) throws SQLException {
        var bandeiraCartao = new BandeiraCartao();

        int id = rs.getInt("id");
        String descricao = rs.getString("descricao");

        bandeiraCartao.setId(id);
        bandeiraCartao.setDescricao(descricao);
        
        return bandeiraCartao;
    }
}
