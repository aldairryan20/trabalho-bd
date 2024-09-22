package com.example.demo.entity.pessoa.cliente;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.example.demo.interfaces.EntityFactory;
@Component
public class ClienteFactory implements EntityFactory<Cliente> {
    @Override
    public Cliente createFromResultSet(ResultSet rs) throws SQLException {
        var cliente = new Cliente();

        int id = rs.getInt("id");
        String fatorRisco = rs.getString("fator_risco");
        double rendaMensal = rs.getDouble("renda_mensal");

        cliente.setId(id);
        cliente.setFatorRisco(fatorRisco);
        cliente.setRendaMensal(rendaMensal);
        return cliente;
    }
}
