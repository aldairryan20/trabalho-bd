package com.example.demo.entity.pessoa;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.example.demo.interfaces.EntityFactory;
@Component
public class PessoaFactory implements EntityFactory<Pessoa> {
    @Override
    public Pessoa createFromResultSet(ResultSet rs) throws SQLException {
        var pessoa = new Pessoa();

        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String cpf = rs.getString("cpf");

        pessoa.setId(id);
        pessoa.setNome(nome);
        pessoa.setCpf(cpf);
        
        return pessoa;
    }
}