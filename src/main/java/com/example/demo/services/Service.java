package com.example.demo.services;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.dao.ContaDAO;
import com.example.demo.entity.conta.Conta;
import com.example.demo.repository.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class Service {
    ContaDAO contaDAO;
    Repository repository;
    public JdbcTemplate jdbcTemplate;
    Conta result = jdbcTemplate.queryForObject(
    "SELECT COUNT(*) FROM CONTA", Conta.class);
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
}
