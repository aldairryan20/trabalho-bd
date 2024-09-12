package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.entity.conta.Conta;
import com.example.demo.service.BdService;

import java.sql.SQLException;

@Component
public class ContaDAO {
    @Lazy
    BdService service;
    private Logger logger = LogManager.getLogger(getClass());

    public ArrayList<Conta> findAll() {
        var sql = "SELECT * FROM conta;";
        var contas = new ArrayList<Conta>();

        try(Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                var conta = new Conta();
                conta.setSaldo(rs.getDouble("saldo"));
                conta.setLimiteNegativo(rs.getDouble("limite_negativo"));
                conta.setTipoContaId(rs.getInt("tipo_conta_id"));
                contas.add(conta);
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
            stmt.setInt(1, id);
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

    public void insertConta(int id, double saldo, double limiteNegativo, int tipoContaId) {
        var sql = "INSERT INTO conta(id, saldo, limite_negativo, tipo_conta_id) VALUES (?, ?, ?, ?)";
    
        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setDouble(2, saldo);
            stmt.setDouble(3, limiteNegativo);
            stmt.setInt(4, tipoContaId);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            logger.error("Error: ", e.getMessage());
        }
    }
}
