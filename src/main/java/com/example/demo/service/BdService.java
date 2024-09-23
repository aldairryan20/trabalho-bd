package com.example.demo.service;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.dao.PessoaDAO;
import com.example.demo.dao.ContaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class BdService {
    ContaDAO contaDAO;
    PessoaDAO pessoaDAO;

    public void executeQuery(String sql) {
        
        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    
}
