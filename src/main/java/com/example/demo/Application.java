package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.services.MyService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Application {
	@Autowired
	MyService service;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner createTables() {
		return args -> {
			var sqls = new ArrayList<String>();
			sqls.add("CREATE TABLE IF NOT EXISTS tipo_conta("+
				"id SERIAL PRIMARY KEY,"+
				"descricao VARCHAR(45)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS conta("+
				"id SERIAL PRIMARY KEY,"+
				"saldo DOUBLE PRECISION,"+
				"limite_negativo DOUBLE PRECISION,"+
				"cliente_id INT,"+
				"tipo_conta_id INT,"+
				"FOREIGN KEY (cliente_id) REFERENCES cliente(id),"+
				"FOREIGN KEY (tipo_conta_id) REFERENCES tipo_conta(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS pessoa("+
				"id SERIAL PRIMARY KEY,"+
				"nome VARCHAR(45),"+
				"cpf VARCHAR(45)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS cliente("+
				"id SERIAL PRIMARY KEY,"+
				"pessoa_id INT,"+
				"fator_risco VARCHAR(45),"+
				"renda_mensal DOUBLE PRECISION,"+
				"FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS reserva("+
				"id SERIAL PRIMARY KEY,"+
				"conta_id INT,"+
				"saldo DOUBLE PRECISION,"+
				"taxa DOUBLE PRECISION,"+
				"reservacol VARCHAR(45),"+
				"FOREIGN KEY (conta_id) REFERENCES conta(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS movimentacao_reserva("+
				"id SERIAL PRIMARY KEY,"+
				"reserva_id INT,"+
				"dt_movimentacao TIMESTAMP,"+
				"valor DOUBLE PRECISION,"+
				"tipo_movimentacao VARCHAR(7) CHECK (tipo_movimentacao IN ('entrada', 'saida')),"+
				"FOREIGN KEY (reserva_id) REFERENCES reserva(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS categoria_cartao("+
				"id SERIAL PRIMARY KEY,"+
				"descricao VARCHAR(45)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS cartao_credito("+
				"id SERIAL PRIMARY KEY,"+
				"conta_id INT,"+
				"categoria_cartao_id INT,"+
				"dt_fechamento VARCHAR(45),"+
				"limite_credito DOUBLE PRECISION,"+
				"FOREIGN KEY (conta_id) REFERENCES conta(id),"+
				"FOREIGN KEY (categoria_cartao_id) REFERENCES categoria_cartao(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS movimentacao_conta("+
				"id SERIAL PRIMARY KEY,"+
				"conta_id INT,"+
				"valor DOUBLE PRECISION,"+
				"dt_movimentacao TIMESTAMP,"+
				"tipo_movimentacao VARCHAR(7) CHECK (tipo_movimentacao IN ('entrada', 'saida')),"+
				"FOREIGN KEY (conta_id) REFERENCES conta(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS bandeira_cartao("+
				"id SERIAL PRIMARY KEY,"+
				"descricao VARCHAR(45)"+
			");");
			
			sqls.add("CREATE TABLE IF NOT EXISTS cartao_transacao("+
				"id SERIAL PRIMARY KEY,"+
				"cartao_id INT,"+
				"numero_cartao VARCHAR(20),"+
				"cvc VARCHAR(5),"+
				"tipo_cartao VARCHAR(7) CHECK (tipo_cartao IN ('fisico', 'virtual')),"+
				"nome_cartao VARCHAR(255),"+
				"tipo_transacao VARCHAR(7) CHECK (tipo_transacao IN ('credito', 'debito')),"+
				"is_internacional BOOLEAN,"+
				"bandeira_cartao_id INT,"+
				"FOREIGN KEY (cartao_id) REFERENCES cartao_credito(id),"+
				"FOREIGN KEY (bandeira_cartao_id) REFERENCES bandeira_cartao(id)"+
			");");
			
			sqls.add("CREATE TABLE IF NOT EXISTS movimentacao_cartao("+
				"id SERIAL PRIMARY KEY,"+
				"cartao_transacao_id INT,"+
				"dt_movimentacao TIMESTAMP,"+
				"valor DOUBLE PRECISION,"+
				"tipo_movimentacao VARCHAR(45),"+
				"FOREIGN KEY (cartao_transacao_id) REFERENCES cartao_transacao(id)"+
			");");
			
			sqls.add("CREATE TABLE IF NOT EXISTS corretor("+
				"id SERIAL PRIMARY KEY,"+
				"nome VARCHAR(45)"+
			");");
			
			sqls.add("CREATE TABLE IF NOT EXISTS compra("+
				"id SERIAL PRIMARY KEY,"+
				"valor DOUBLE PRECISION,"+
				"quantidade_parcela INT,"+
				"taxa_parcelamento DOUBLE PRECISION,"+
				"credor VARCHAR(45),"+
				"corretor_id INT,"+
				"cartao_transacao_id INT,"+
				"data_compra TIMESTAMP,"+
				"FOREIGN KEY (corretor_id) REFERENCES corretor(id),"+
				"FOREIGN KEY (cartao_transacao_id) REFERENCES cartao_transacao(id)"+
			");");
		



			for(String sql:sqls){
				service.executeQuery(sql);
			}
		};
	}
	@PostConstruct
	void tabelas() {
		var sql = "SELECT table_name\r\n" + //
						"FROM information_schema.tables\r\n" + //
						"WHERE table_schema = 'public';";
		System.out.println(service.getTables(sql));
	}
}
