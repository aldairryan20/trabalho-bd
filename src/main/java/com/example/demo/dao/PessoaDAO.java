package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.entity.pessoa.Pessoa;
import com.example.demo.entity.pessoa.PessoaFactory;
import com.example.demo.interfaces.CrudInterfaceImpl;

@Component
public class PessoaDAO extends CrudInterfaceImpl<Pessoa> {
    private final PessoaFactory pessoaFactory;
    private final Logger logger = LogManager.getLogger(getClass());

    public PessoaDAO(PessoaFactory pessoaFactory) {
        super("pessoa");
        this.pessoaFactory = pessoaFactory;
    }

    @Override
    public Pessoa createElementFromResultSet(ResultSet rs) throws SQLException {
        return pessoaFactory.createFromResultSet(rs);
    }

    public void insertPessoa(String cpf, int id, String nome) {
        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        var sql = "INSERT INTO pessoa (id, nome, cpf) VALUES (?, ?, ?)";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, nome);
            stmt.setString(3, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error at " + getClass().getName() + "\nMethod = " + methodName + "\n", e.getMessage());
            e.printStackTrace();
        }
    }

    public ResponseEntity<String> update(String nome, String cpf, int id) {
        var sql = "UPDATE Pessoa SET cpf = ?, nome = ? WHERE id = ?";

        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setInt(3, id);
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

    
    
    //DELETE FROM Pessoa WHERE cpf = '123.456.789-00';
    //DELETE FROM Pessoa WHERE id = 1;

}
