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

@Component
public class PessoaDAO {
    private Logger logger = LogManager.getLogger(getClass());

    public HashMap<Integer, Pessoa> findAll() {
        var sql = "SELECT * FROM pessoa;";
        var pessoas = new HashMap<Integer, Pessoa>();

        try(Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                var pessoa = new Pessoa();
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));

                pessoas.put(pessoa.getId(), pessoa);
            }
        } catch (SQLException e) {
            logger.error("Error at: "+ getClass().getName(), e.getMessage());
        }
        return pessoas;
    }
    
}
