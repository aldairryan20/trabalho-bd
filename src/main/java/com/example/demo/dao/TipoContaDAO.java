package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.entity.conta.TipoConta;
import com.example.demo.entity.conta.TipoContaFactory;
import com.example.demo.interfaces.CrudInterfaceImpl;

@Component
public class TipoContaDAO extends CrudInterfaceImpl<TipoConta> {
    private final TipoContaFactory tipoContaFactory;

    public TipoContaDAO(TipoContaFactory tipoContaFactory) {
        super("tipo_conta");
        this.tipoContaFactory = tipoContaFactory;
    }

    @Override
    public TipoConta createElementFromResultSet(ResultSet rs) throws SQLException {
        return tipoContaFactory.createFromResultSet(rs);
    }

    public void insertTipoConta(int id, String descricao) {
        var sql = "INSERT INTO tipo_conta(id, descricao) VALUES (?, ?)";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, descricao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity<String> update(String descricao, int id) {
        var sql = "UPDATE Pessoa SET cpf = ?, nome = ? WHERE id = ?";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, descricao);
            stmt.setInt(2, id);
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
