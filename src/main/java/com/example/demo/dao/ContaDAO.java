package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.entity.conta.Conta;

import java.sql.SQLException;

public class ContaDAO {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ContaDAO.class);
    public HashMap<Integer, Conta> findAll() {
        var sql = "SELECT * FROM conta";
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
            // TODO: handle exception
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
                conta.setTipoContaId(rs.getInt("tipoContaId"));
            }
        } catch (SQLException e) {
            logger.error("Not founded", e);
        }
        return conta;
    }
}
