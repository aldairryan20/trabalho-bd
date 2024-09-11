package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.entity.conta.Conta;

import java.sql.SQLException;

@Component
public class ContaDAO {
    private Logger logger = LogManager.getLogger(getClass());
    public HashMap<Integer, Conta> findAll() {
        var sql = "SELECT * FROM conta;";
        var contas = new HashMap<Integer, Conta>();

        try(Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                var conta = new Conta();
                conta.setSaldo(rs.getDouble("saldo"));
                conta.setLimiteNegativo(rs.getDouble("limite_negativo"));
                conta.setTipoContaId(rs.getInt("tipoContaId"));
                contas.put(conta.getId(), conta);
            }
        } catch (SQLException e) {
            logger.error("Error at: "+ getClass().getName(), e.getMessage());
        }
        return contas;
    }

    public Conta findById(int id) {
        var sql = new String("SELECT FROM conta WHERE id = ?");
        var conta = new Conta();

        try(Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                conta.setSaldo(rs.getDouble("saldo"));
                conta.setLimiteNegativo(rs.getDouble("limite_negativo"));
                conta.setTipoContaId(rs.getInt("tipo_conta_id"));
            }
        } catch (SQLException e) {
            logger.error("conta not founded - "+ id +"\n", e.getMessage());
        }
        return conta;
    }
}
