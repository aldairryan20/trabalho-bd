package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.entity.cartao.CategoriaCartao;
import com.example.demo.interfaces.CrudInterfaceImpl;

@Component
public class CategoriaCartaoDAO extends CrudInterfaceImpl<CategoriaCartao> {

    public CategoriaCartaoDAO() {
        super("categoria_cartao");
    }

    @Override
    public CategoriaCartao createElementFromResultSet(ResultSet rs) throws SQLException {
        CategoriaCartao categoriaCartao = new CategoriaCartao();
        categoriaCartao.setId(rs.getInt("id"));
        categoriaCartao.setDescricao(rs.getString("descricao"));
        return categoriaCartao;
    }

    public void insertCategoriaCartao(int id, String descricao) {
        var sql = "INSERT INTO categoria_cartao (id, descricao) VALUES (?, ?)";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, descricao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity<String> updateCategoriaCartao(int id, String descricao) {
        var sql = "UPDATE categoria_cartao SET descricao = ? WHERE id = ?";

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
