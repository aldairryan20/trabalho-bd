package com.example.demo.entity.compra.boleto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.example.demo.interfaces.EntityFactory;

@Component
public class TipoBoletoFactory implements EntityFactory<TipoBoleto> {

    @Override
    public TipoBoleto createFromResultSet(ResultSet rs) throws SQLException {
        var tipoBoleto = new TipoBoleto();

        int id = rs.getInt("id");
        String descricao = rs.getString("descricao");

        tipoBoleto.setId(id);
        tipoBoleto.setDescricao(descricao);
        
        return tipoBoleto;
    }
    
}
