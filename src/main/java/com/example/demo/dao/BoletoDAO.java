package com.example.demo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.entity.compra.boleto.Boleto;
import com.example.demo.entity.compra.boleto.BoletoFactory;
import com.example.demo.interfaces.CrudInterfaceImpl;

@Component
public class BoletoDAO extends CrudInterfaceImpl<Boleto> {
    TipoBoletoDAO tipoBoletoDAO;
    BoletoFactory factory;
    
    public BoletoDAO(BoletoFactory factory, TipoBoletoDAO tipoBoletoDAO) {
        super("boleto");
        this.factory = factory;
    }

    public void insertBoleto(int id, double valor, Date dataVencimento, Date dataGeracao, String codigoBarras, int tipoBoletoId, int faturaCartaoId) {
        var sql = "INSERT INTO boleto(id, valor, dt_vencimento, dt_geracao, codigo_barras, tipo_boleto_id, fatura_cartao_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setDouble(2, valor);
            stmt.setDate(3, dataVencimento);
            stmt.setDate(4, dataGeracao);
            stmt.setString(5, codigoBarras);
            stmt.setInt(6, tipoBoletoId);
            stmt.setInt(7, faturaCartaoId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Boleto getBoleto(String codigoBarras) {
        var sql = "SELECT * FROM boleto WHERE codigo_barras = ?";
        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, codigoBarras);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                var boleto = factory.createFromResultSet(rs);
                return boleto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public ResponseEntity<String> update(double valor, Date dataVencimento, Date dataGeracao, String codigoBarras, int tipoBoletoId, int faturaCartaoId, int id) {
        var sql = "UPDATE boleto SET valor = ?, dt_vencimento = ?, dt_geracao = ?, codigo_barras = ?, tipo_boleto_id = ?, fatura_cartao_id = ? WHERE id = ?";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setDouble(2, valor);
            stmt.setDate(3, dataVencimento);
            stmt.setDate(4, dataGeracao);
            stmt.setString(5, codigoBarras);
            stmt.setInt(6, tipoBoletoId);
            stmt.setInt(7, faturaCartaoId);
            
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

    @Override
    public void delete(int id) throws SQLException{
        super.delete(id);
        tipoBoletoDAO.delete(id);
    }
    
}
