package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.entity.pessoa.Pessoa;
import com.example.demo.service.MyService;

@Component
public class PessoaDAO {
    MyService service;
    private Logger logger = LogManager.getLogger(getClass());

    public HashMap<Integer, String> findAll() {
        var sql = "SELECT * FROM pessoa;";
        var pessoas = new HashMap<Integer, String>();

        try(Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                var pessoa = new Pessoa();
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));

                pessoas.put(pessoa.getId(), pessoa.getNome());
            }
        } catch (SQLException e) {
            logger.error("Error at: "+ getClass().getName(), e.getMessage());
        }
        return pessoas;
    }

    public Pessoa findPessoaById(int id) {
        var sql = "SELECT * FROM pessoa WHERE id = ?";
        var pessoa = new Pessoa();
    
        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            //stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            logger.error("Pessoa não encontrada - " + id + "\n", e);
        }
        return pessoa;
    }
    

    public void insertPessoa(String cpf, int id, String nome) {
        var sql = "INSERT INTO pessoa (cpf, id, nome) VALUES (?, ?, ?)";
    
        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.setInt(2, id);
            stmt.setString(3, nome);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Erro", e.getMessage());
        }
    }
    
}
