package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.entity.cartao.CartaoTransacao;
import com.example.demo.entity.cartao.CartaoTransacaoFactory;
import com.example.demo.interfaces.CrudInterfaceImpl;

@Component
public class CartaoTransacaoDAO extends CrudInterfaceImpl<CartaoTransacao> {
    private final CartaoTransacaoFactory factory;

    public CartaoTransacaoDAO(CartaoTransacaoFactory factory) {
        super("cartao_transacao");
        this.factory = factory;
    }

    @Override
    public CartaoTransacao createElementFromResultSet(ResultSet rs) throws SQLException {
        return factory.createFromResultSet(rs);
    }

    public void insertCartaoTransacao(int id, String numCartao, String cvc, int cartaoCreditoId, String tipoCartao, String nomeCartao, String tipoTransacao, boolean isInternacional, int bandeiraCartao) {
        var sql = "INSERT INTO cartao_transacao (id, numero_cartao, cvc, cartao_credito_id, tipo_cartao, nome_cartao, tipo_transacao, is_internacional, bandeira_cartao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, numCartao);
            stmt.setString(3, cvc);
            stmt.setInt(4, cartaoCreditoId);
            stmt.setString(5, tipoCartao);
            stmt.setString(6, nomeCartao);
            stmt.setString(7, tipoTransacao);
            stmt.setBoolean(8, isInternacional);
            stmt.setInt(9, bandeiraCartao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity<String> updateCartaoTransacao(int id, String numCartao, String cvc, int cartaoCreditoId, String tipoCartao, String nomeCartao, String tipoTransacao, boolean isInternacional, int bandeiraCartao) {
        var sql = "UPDATE cartao_transacao SET numero_cartao = ?, cvc = ?, cartao_credito_id = ?, tipo_cartao = ?, nome_cartao = ?, tipo_transacao = ?, is_internacional = ?, bandeira_cartao = ? WHERE id = ?";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, numCartao);
            stmt.setString(2, cvc);
            stmt.setInt(3, cartaoCreditoId);
            stmt.setString(4, tipoCartao);
            stmt.setString(5, nomeCartao);
            stmt.setString(6, tipoTransacao);
            stmt.setBoolean(7, isInternacional);
            stmt.setInt(8, bandeiraCartao);
            stmt.setInt(9, id);
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
