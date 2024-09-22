package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.entity.compra.fatura.ItensFatura;
import com.example.demo.entity.pessoa.cliente.Cliente;
import com.example.demo.entity.pessoa.cliente.ClienteFactory;
import com.example.demo.interfaces.CrudInterfaceImpl;

@Component
public class ClienteDAO extends CrudInterfaceImpl<Cliente> {
    private final ClienteFactory clienteFactory;

    public ClienteDAO(ClienteFactory clienteFactory) {
        super("cliente");
        this.clienteFactory = clienteFactory;
    }

    @Override
    public Cliente createElementFromResultSet(ResultSet rs) throws SQLException {
        return clienteFactory.createFromResultSet(rs);
    }

    public void insertCliente(int id, String fatorRisco, double rendaMensal, int cartaoId) {
        var sql = "INSERT INTO cliente (id, fator_risco, renda_mensal, cartao_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, fatorRisco);
            stmt.setDouble(3, rendaMensal);
            stmt.setInt(4, cartaoId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity<String> updateCliente(int id, String fatorRisco, double rendaMensal, int cartaoId) {
        var sql = "UPDATE cliente SET fator_risco = ?, renda_mensal = ?, cartao_id = ? WHERE id = ?";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fatorRisco);
            stmt.setDouble(2, rendaMensal);
            stmt.setInt(3, cartaoId);
            stmt.setInt(4, id);
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

    public List<ItensFatura> findItensFaturaByClienteId(int clienteId) {
        var sql = "SELECT * FROM itens_fatura WHERE fatura_cartao_id IN (SELECT id FROM fatura_cartao WHERE cartao_credito_id = (SELECT cartao_id FROM cliente WHERE id = ?))";
        List<ItensFatura> itensFatura = new ArrayList<>();

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ItensFatura item = new ItensFatura();
                item.setId(rs.getInt("id"));
                item.setDescricao(rs.getString("descricao"));
                item.setFaturaCartaoId(rs.getInt("fatura_cartao_id"));
                itensFatura.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itensFatura;
    }
}
