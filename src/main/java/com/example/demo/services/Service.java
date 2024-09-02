package com.example.demo.services;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.entity.conta.Conta;

public class Service {
    public JdbcTemplate jdbcTemplate;
    Conta result = jdbcTemplate.queryForObject(
    "SELECT COUNT(*) FROM CONTA", Conta.class);

}
