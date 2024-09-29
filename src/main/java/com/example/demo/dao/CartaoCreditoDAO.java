package com.example.demo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.entity.cartao.CartaoCredito;
import com.example.demo.entity.cartao.CartaoCreditoFactory;
import com.example.demo.interfaces.CrudInterfaceImpl;
import com.example.demo.rest.exception.NotFoundException;

@Component
public class CartaoCreditoDAO extends CrudInterfaceImpl<CartaoCredito> {
    private final CartaoCreditoFactory factory;

    public CartaoCreditoDAO(CartaoCreditoFactory factory) {
        super("cartao_credito");
        this.factory = factory;
    }
    @Override
    public CartaoCredito createElementFromResultSet(ResultSet rs) throws SQLException {
        return factory.createFromResultSet(rs);
    }

    public void insertCartaoCredito(int id, Date dataFechamento, int contaId, int catCartaoId, double limiteCredito, int bandeiraId) {
        var sql = "INSERT INTO cartao_credito (id, dt_fechamento, conta_id, categoria_cartao_id, limite_credito, bandeira_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setDate(2, dataFechamento);
            stmt.setInt(3, contaId);
            stmt.setInt(4, catCartaoId);
            stmt.setDouble(5, limiteCredito);
            stmt.setInt(6, bandeiraId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity<String> updateCartaoCredito(int id, Date dataFechamento, int contaId, int catCartaoId, double limiteCredito, int bandeiraId) {
        var sql = "UPDATE cartao_credito SET dt_fechamento = ?, conta_id = ?, categoria_cartao_id = ?, limite_credito = ?, bandeira_id = ? WHERE id = ?";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, dataFechamento);
            stmt.setInt(2, contaId);
            stmt.setInt(3, catCartaoId);
            stmt.setDouble(4, limiteCredito);
            stmt.setInt(5, bandeiraId);
            stmt.setInt(6, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return ResponseEntity.ok("updated");
            } else {
                throw new NotFoundException(getClass().getName()+": Could not found - "+id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error at update", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
