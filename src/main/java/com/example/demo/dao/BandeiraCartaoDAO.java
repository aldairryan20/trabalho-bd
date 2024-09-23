package com.example.demo.dao;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.entity.cartao.BandeiraCartao;
import com.example.demo.entity.cartao.BandeiraCartaoFactory;
import com.example.demo.interfaces.CrudInterfaceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BandeiraCartaoDAO extends CrudInterfaceImpl<BandeiraCartao> {
    private final BandeiraCartaoFactory bandeiraCartaoFactory;

    public BandeiraCartaoDAO(BandeiraCartaoFactory bandeiraCartaoFactory) {
        super("bandeira_cartao");
        this.bandeiraCartaoFactory = bandeiraCartaoFactory;
    }

    @Override
    public BandeiraCartao createElementFromResultSet(ResultSet rs) throws SQLException {
        return bandeiraCartaoFactory.createFromResultSet(rs);
    }

    public void insertBandeiraCartao(int id, String descricao) {
        var sql = "INSERT INTO bandeira_cartao (id, descricao) VALUES (?, ?)";

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
        var sql = "UPDATE bandeira_cartao SET descricao = ? WHERE id = ?";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, descricao);
            stmt.setInt(2, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return ResponseEntity.ok("updated");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found - " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error at update", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

