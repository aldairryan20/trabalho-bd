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
    @SuppressWarnings("deprecation")
    public Conta findById(int id) {
        String sql = "SELECT FROM CONTA WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Conta>() {
            @Override
            public Conta mapRow(ResultSet rs, int numRow) throws SQLException {
                var conta = new Conta();
                conta.setId(rs.getInt("id"));
                conta.setNome(rs.getString("nome"));
                return conta;
            }
        });
    };
    
    public Cliente getAll(){
        var clientes = jdbcTemplate.queryForObject("SELECT * FROM clientes;", Cliente.class);

        return clientes;
    }
}
