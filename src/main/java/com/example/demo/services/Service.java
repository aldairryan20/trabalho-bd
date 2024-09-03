package com.example.demo.services;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.conta.Conta;
import com.example.demo.repository.Repository;

public class Service {
    Repository repository;
    public JdbcTemplate jdbcTemplate;
    Conta result = jdbcTemplate.queryForObject(
    "SELECT COUNT(*) FROM CONTA", Conta.class);
    public Cliente findById(int id) {
        return repository.findById(id);
    }

}
