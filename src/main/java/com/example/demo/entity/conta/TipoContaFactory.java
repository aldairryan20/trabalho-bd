package com.example.demo.entity.conta;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.example.demo.interfaces.EntityFactory;

@Component
public class TipoContaFactory implements EntityFactory<TipoConta> {
    @SuppressWarnings("unused")
    @Override
    public TipoConta createFromResultSet(ResultSet rs) throws SQLException {
        var tipoConta = new TipoConta();

        int id = rs.getInt("id");
        String descricao = rs.getString("descricao");

        tipoConta.setId(id);
        tipoConta.setDescricao(descricao);

        return tipoConta;
    }
}