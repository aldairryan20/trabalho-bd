package com.example.demo.services;

import com.example.demo.config.SpringJdbcConfig;
import com.example.demo.dao.ContaDAO;
import com.example.demo.entity.conta.Conta;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class ContaService {
    ContaDAO contaDAO;
    public ContaService() {
        var contaDAO = new ContaDAO();
        this.contaDAO = contaDAO;
        System.out.println("service created");
    }
    public Conta findById(int id) {
        return contaDAO.findById(id);
    }

    public HashMap<Integer, Conta> findAll() {
        return contaDAO.findAll();
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

        }
        return jsonResult;
    }

    public void executeQuery(String sql) {
        try (Connection conn = SpringJdbcConfig.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            System.out.println("Conexão estabelecida com sucesso!"+pstm.getConnection());
            pstm.execute();
        } catch(SQLException e){
            System.out.println("Erro");
        }
    }
}
