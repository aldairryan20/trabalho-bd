package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.services.ContaService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Application {
	@Autowired
	ContaService service;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner createTables() {
		return args -> {
			var sql0 = "CREATE TABLE tipo_conta("+
				"id INT,"+
				"descricao VARCHAR(45)"+
			");";
			var sql1 = "CREATE TABLE conta("+
				"id INT NOTNULL PRIMARY KEY,"+
				"saldo DOUBLE,"+
				"limite_negativo DOUBLE,"+
				"FOREIGN KEY (cliente_id) REFERENCES cliente(id),"+
				"FOREIGN KEY (tipo_conta_id) REFERENCES tipo_conta(id)"+
			");";
			var sql2 = "CREATE TABLE pessoa("+
				"id INT,"+
				"nome VARCHAR(45),"+
				"cpf VARCHAR(45)"+
			");";
			var sql3 = "CREATE TABLE cliente("+
				"id INT,"+
				"fator_risco VARCHAR(45),"+
				"renda_mensal DOUBLE,"+
				"FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),"+
			");";
			var slq4 = "CREATE TABLE reserva("+
				"id INT,"+
				"saldo DOUBLE,"+
				"taxa DOUBLE,"+
				"reservacol VARCHAR(45),"+
				"FOREIGN KEY (conta_id) REFERENCES conta(id)"+
			");";
			var sql5 = "CREATE TABLE movimentacao_reserva("+
				"id INT,"+
				"dt_movimentacao DATETIME,"+
				"valor DOUBLE,"+
				"CHECK (tipo_movimentacao IN ('entrada', 'saida')),"+
				"FOREIGN KEY (reserva_id) REFERENCES reserva(id)"+
				");";
			var sql6 = "CREATE TABLE categoria_cartao("+
				"id INT,"+
				"descricao VARCHAR(45)"+
			");";
			var sql7 = "CREATE TABLE cartao_credito("+
				"id INT,"+
				"dt_fechamento VARCHAR(45),"+
				"FOREIGN KEY (conta_id) REFERENCES conta(id),"+
				"FOREIGN KEY (categoria_cartao_id) REFERENCES categoria_cartao(id),"+
				"limite_credito DOUBLE"+			
			");";
			var sql8 = "CREATE TABLE movimentacao_conta("+
				"id INT,"+
				"valor DOUBLE,"+
				"dt_movimentacao DATETIME,"+
				"CHECK (tipo_movimentacao IN ('entrada', 'saida')),"+
				"FOREIGN KEY (conta_id) REFERENCES conta(id)"+
			");";
			var sql9 = "CREATE TABLE bandeira_cartao("+
				"id INT,"+
				"descricao VARCHAR(45)"+
			");";
			var sql10 = "CREATE TABLE cartao_transacao("+
				"id INT,"+
				"numero_cartao VARCHAR(20),"+
				"cvc VARCHAR(5),"+
				"FOREIGN KEY (cartao_id) REFERENCES cartao_credito(id)"+
				"CHECK (tipo_cartao IN ('fisico', 'virtual')),"+
				"nome_cartao VARCHAR 255"+
				"CHECK (tipo_transacao IN ('credito', 'debito')),"+
				"is_internacional TINYINT,"+
				"FOREIGN KEY (bandeira_cartao_id) REFERENCES bandeira_cartao(id)"
				+")";
			var sql11 = "CREATE TABLE movimentacao_cartao("+
				"id INT,"+
				"dt_movimentacao DATETIME,"+
				"valor DOUBLE,"+
				"FOREIGN KEY (cartao_transacao_id) REFERENCES cartao_transacao(id),"+
				"tipo_movimentacao VARCHAR(45)"+
			");";
			var sql12 = "CREATE TABLE corretor("+
				"id INT,"+
				"nome VARCHAR(45)"+
			");";
			var sql13 = "CREATE TABLE compra("+
				"id INT,"+
				"valor DOUBLE,"+
				"quantidade_parcela INT,"+
				"taxa_parcelamento DOUBLE,"+
				"credor VARCHAR(45),"+
				"FOREIGN KEY (corretor_id) REFERENCES corretor(id),"+
				"FOREIGN KEY (cartao_transacao_id) REFERENCES cartao_transacao(id),"+
				"data_compra DATETIME"+
			");";
		};
	}
	@PostConstruct
	void executar() {
		var sql0 = "CREATE TABLE tipo_conta("+
				"id INT,"+
				"descricao VARCHAR(45)"+
			");";
		service.executeQuery(sql0);
	}
}
