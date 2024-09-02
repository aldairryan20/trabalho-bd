package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.entity.Pessoa;
import com.example.demo.entity.conta.Conta;
import com.example.demo.services.ContaService;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class Application {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
	ContaService service;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	static void loadPessoa() {
		var p = new Pessoa( 1, "nome", "12345678900");
		var conta = new Conta(1, 1000.0, -500.0);
	}

    @Bean
    public CommandLineRunner runner() {
        return (args) -> {
			loadPessoa();

            String query = "INSERT INTO conta (id, saldo, limite_negativo, cliente_id, tipo_conta_id) VALUES (?, ?, ?, ?, ?)";
            int result = jdbcTemplate.update(query, 1, 1000.0, -500.0, 1, 1);

            if (result > 0) {
                System.out.println("Um novo registro foi inserido com sucesso.");
            }
			
        };
    }
}
