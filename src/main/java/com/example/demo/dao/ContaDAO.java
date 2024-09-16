package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.entity.conta.Conta;
import com.example.demo.entity.conta.ContaFactory;
import com.example.demo.interfaces.CrudInterfaceImpl;

import java.sql.SQLException;

@Component
public class ContaDAO extends CrudInterfaceImpl<Conta> {
    private TipoContaDAO tipoContaDAO;
    private final ContaFactory contaFactory;

    public ContaDAO(ContaFactory contaFactory, TipoContaDAO tipoContaDAO) {
        super("conta");
        this.contaFactory = contaFactory;
        this.tipoContaDAO = tipoContaDAO;
    }

    @Override
    public Conta createElementFromResultSet(ResultSet rs) throws SQLException {
        return contaFactory.createFromResultSet(rs);
    }

    public void insertConta(int id, double saldo, double limiteNegativo, int tipoContaId) {
        var sql = "INSERT INTO conta (id, saldo, limite_negativo, tipo_conta) VALUES (?, ?, ?, ?)";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            tipoContaDAO.insertTipoConta(tipoContaId, "tipoContaTeste");

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setDouble(2, saldo);
            stmt.setDouble(3, limiteNegativo);
            stmt.setInt(4, tipoContaId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity<String> update(int id, double saldo, double limiteNegativo, int tipoContaId) {
        var sql = "UPDATE Pessoa SET saldo = ?, limite_negativo = ?, tipo_conta = ? WHERE id = ?";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, saldo);
            stmt.setDouble(2, limiteNegativo);
            stmt.setInt(3, tipoContaId);
            stmt.setInt(4, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return ResponseEntity.ok("updated");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found - "+ id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error at update", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
