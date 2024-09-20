package com.example.demo.dao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.entity.compra.boleto.TipoBoleto;
import com.example.demo.entity.compra.boleto.TipoBoletoFactory;
import com.example.demo.interfaces.CrudInterfaceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TipoBoletoDAO extends CrudInterfaceImpl<TipoBoleto> {
    private final TipoBoletoFactory factory;

    public TipoBoletoDAO(TipoBoletoFactory factory) {
        super("tipo_boleto");
        this.factory = factory;
    }

    @Override
    public TipoBoleto createElementFromResultSet(ResultSet rs) throws SQLException {
        return factory.createFromResultSet(rs);
    }

    public void insertTipoBoleto(int id, String descricao) {
        var sql = "INSERT INTO tipo_boleto(id, descricao) VALUES (?, ?)";

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
        var sql = "UPDATE tipo_boleto SET descricao = ? WHERE id = ?";

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
