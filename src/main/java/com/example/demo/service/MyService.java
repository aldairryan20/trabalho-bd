package com.example.demo.service;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.dao.ContaDAO;
import com.example.demo.entity.conta.Conta;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    Logger logger = LogManager.getLogger(getClass());
    ContaDAO contaDAO;
    public MyService() {
        var contaDAO = new ContaDAO();
        this.contaDAO = contaDAO;
        System.out.println("service created");
    }

    public String findAllContasAsJson() {
        HashMap<Integer, Conta> contas = contaDAO.findAll();
        var objectMapper = new ObjectMapper();
        var jsonResult = "";
        try{
            jsonResult = objectMapper.writeValueAsString(contas);
        } catch(Exception e){

        }
        return jsonResult;
    }

    public String findByIdAsJson(int id) {
        var conta = contaDAO.findById(id);
        var objectMapper = new ObjectMapper();
        var jsonResult = "";
        try{
            jsonResult = objectMapper.writeValueAsString(conta);
        } catch(Exception e){
            logger.error("\nErro em: " + getClass().getName(), e);
        }
        return jsonResult;
    }

    public void executeQuery(String sql) {
        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            System.out.println("Conexão estabelecida com sucesso!"+pstm.getConnection());
            pstm.executeUpdate();
            logger.info(sql);
        } catch(SQLException e){
            logger.error("\nErro em: " + getClass().getName(), e);
        }
    }
    public String getTables(String sql) {
        StringBuilder result = new StringBuilder();
        try (Connection conn = SpringJdbcConfig.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            logger.info(sql);
            while (rs.next()) {
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    result.append(rs.getString(i)).append(" ");
                }
                result.append("\n");
            }
        } catch(SQLException e) {
            logger.error("Erro em: " + getClass().getName(), e);
        }
        return result.toString();
    }    
}
