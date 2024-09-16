package com.example.demo.entity.conta;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.interfaces.EntityFactory;

public class ContaFactory implements EntityFactory<Conta> {
    @SuppressWarnings("unused")
    @Override
    public Conta createFromResultSet(ResultSet rs) throws SQLException {
        var conta = new Conta();
        int id = rs.getInt("id");
        double saldo = rs.getDouble("saldo");;
        double limiteNegativo = rs.getDouble("limite_negativo");;
        int tipoContaId = rs.getInt("tipo_conta_id"); // 1: poupança, 2: corrente
        return conta;
    }
}