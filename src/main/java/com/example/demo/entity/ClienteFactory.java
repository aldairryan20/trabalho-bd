package com.example.demo.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.interfaces.EntityFactory;

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
