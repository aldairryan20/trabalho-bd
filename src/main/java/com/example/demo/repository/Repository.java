package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.conta.Conta;

public class Repository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
