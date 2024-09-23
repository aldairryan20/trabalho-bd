package com.example.demo.entity.conta;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.example.demo.interfaces.EntityFactory;
@Component
public class ContaFactory implements EntityFactory<Conta> {
    @Override
    public Conta createFromResultSet(ResultSet rs) throws SQLException {
        var conta = new Conta();
        int id = rs.getInt("id");
        double saldo = rs.getDouble("saldo");;
        double limiteNegativo = rs.getDouble("limite_negativo");;
        int tipoContaId = rs.getInt("tipo_conta_id");

        conta.setId(id);
        conta.setSaldo(saldo);
        conta.setLimiteNegativo(limiteNegativo);
        conta.setTipoContaId(tipoContaId);
        return conta;
    }
}