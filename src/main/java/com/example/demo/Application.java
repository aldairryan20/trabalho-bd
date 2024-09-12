package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import com.example.demo.entity.conta.Conta;
import com.example.demo.entity.pessoa.Pessoa;
import com.example.demo.service.BdService;

@SpringBootApplication
public class Application {
	@Autowired
	BdService service;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@Order(1)
	public CommandLineRunner createTables() {
		var sqls = new ArrayList<String>();
		return (args) -> {

			sqls.add("CREATE TABLE IF NOT EXISTS bandeira_cartao("+
				"id SERIAL PRIMARY KEY,"+
				"descricao VARCHAR(45)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS categoria_cartao("+
				"id SERIAL PRIMARY KEY,"+
				"descricao VARCHAR(45)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS tipo_boleto("+
				"id SERIAL PRIMARY KEY,"+
				"descricao VARCHAR(45)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS corretor("+
				"id SERIAL PRIMARY KEY,"+
				"nome VARCHAR(45)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS pessoa("+
				"id SERIAL PRIMARY KEY,"+
				"nome VARCHAR(45),"+
				"cpf VARCHAR(45)"+
			");");
			// ================================================
			sqls.add("CREATE TABLE IF NOT EXISTS tipo_conta("+
				"id SERIAL PRIMARY KEY,"+
				"descricao VARCHAR(45)"+
			");");
			sqls.add("CREATE TABLE IF NOT EXISTS conta("+
				"id SERIAL PRIMARY KEY,"+
				"saldo DOUBLE PRECISION,"+
				"limite_negativo DOUBLE PRECISION,"+
				"tipo_conta_id SERIAL,"+
				"FOREIGN KEY (tipo_conta_id) REFERENCES tipo_conta(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS cartao_credito("+
				"id SERIAL PRIMARY KEY,"+
				"conta_id SERIAL,"+
				"categoria_cartao_id SERIAL,"+
				"dt_fechamento VARCHAR(45),"+
				"limite_credito DOUBLE PRECISION,"+
				"FOREIGN KEY (conta_id) REFERENCES conta(id),"+
				"FOREIGN KEY (categoria_cartao_id) REFERENCES categoria_cartao(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS cartao_transacao("+
				"id SERIAL PRIMARY KEY,"+
				"numero_cartao VARCHAR(20),"+
				"cvc VARCHAR(5),"+
				"tipo_cartao VARCHAR(7) CHECK (tipo_cartao IN ('fisico', 'virtual')),"+
				"nome_cartao VARCHAR(255),"+
				"tipo_transacao VARCHAR(7) CHECK (tipo_transacao IN ('credito', 'debito')),"+
				"is_internacional BOOLEAN,"+
				"cartao_credito_id SERIAL,"+
				"bandeira_cartao_id SERIAL,"+
				"FOREIGN KEY (cartao_credito_id) REFERENCES cartao_credito(id),"+
				"FOREIGN KEY (bandeira_cartao_id) REFERENCES bandeira_cartao(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS fatura_cartao("+
				"id SERIAL PRIMARY KEY,"+
				"mes_ref VARCHAR(9),"+
				"ano_ref VARCHAR(4),"+
				"valor DOUBLE PRECISION,"+
				"dt_pagamento TIMESTAMP,"+
				"cartao_credito_id SERIAL,"+
				"FOREIGN KEY (cartao_credito_id) REFERENCES cartao_credito(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS boleto("+
				"id SERIAL PRIMARY KEY,"+
				"valor DOUBLE PRECISION,"+
				"dt_vencimento TIMESTAMP,"+
				"dt_geracao TIMESTAMP,"+
				"codigo_barras VARCHAR(45),"+
				"tipo_boleto_id SERIAL,"+
				"fatura_cartao_id SERIAL,"+
				"FOREIGN KEY (tipo_boleto_id) REFERENCES tipo_boleto(id),"+
				"FOREIGN KEY (fatura_cartao_id) REFERENCES fatura_cartao(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS itens_fatura("+
				"id SERIAL,"+
				"descricao VARCHAR(45),"+
				"fatura_cartao_id SERIAL,"+
				"FOREIGN KEY (fatura_cartao_id) REFERENCES fatura_cartao(id)"+
			");");
			
			sqls.add("CREATE TABLE IF NOT EXISTS compra("+
				"id SERIAL PRIMARY KEY,"+
				"valor DOUBLE PRECISION,"+
				"quantidade_parcela INT,"+
				"taxa_parcelamento DOUBLE PRECISION,"+
				"credor VARCHAR(45),"+
				"data_compra TIMESTAMP,"+
				"corretor_id SERIAL,"+
				"cartao_transacao_id SERIAL,"+
				"FOREIGN KEY (corretor_id) REFERENCES corretor(id),"+
				"FOREIGN KEY (cartao_transacao_id) REFERENCES cartao_transacao(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS pagamento("+
				"id SERIAL PRIMARY KEY,"+
				"valor_total DOUBLE PRECISION,"+
				"dt_pagamento TIMESTAMP,"+
				"valor_parcial DOUBLE PRECISION,"+
				"boleto_id SERIAL,"+
				"fatura_cartao_id SERIAL,"+
				"FOREIGN KEY (fatura_cartao_id) REFERENCES fatura_cartao(id),"+
				"FOREIGN KEY (boleto_id) REFERENCES boleto(id)"+
			");");
			
			sqls.add("CREATE TABLE IF NOT EXISTS movimentacao_cartao("+
				"id SERIAL PRIMARY KEY,"+
				"dt_movimentacao TIMESTAMP,"+
				"valor DOUBLE PRECISION,"+
				"tipo_movimentacao VARCHAR(45),"+
				"cartao_transacao_id SERIAL,"+
				"FOREIGN KEY (cartao_transacao_id) REFERENCES cartao_transacao(id)"+
			");");
			
			sqls.add("CREATE TABLE IF NOT EXISTS movimentacao_conta("+
				"id SERIAL PRIMARY KEY,"+
				"valor DOUBLE PRECISION,"+
				"dt_movimentacao TIMESTAMP,"+
				"tipo_movimentacao VARCHAR(7) CHECK (tipo_movimentacao IN ('entrada', 'saida')),"+
				"conta_id SERIAL,"+
				"FOREIGN KEY (conta_id) REFERENCES conta(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS reserva("+
				"id SERIAL PRIMARY KEY,"+
				"dt_movimentacao TIMESTAMP,"+
				"tipo_movimentacao VARCHAR(10),"+
				"conta_id SERIAL,"+
				"FOREIGN KEY (conta_id) REFERENCES conta(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS movimentacao_reserva("+
				"id SERIAL PRIMARY KEY,"+
				"dt_movimentacao TIMESTAMP,"+
				"valor DOUBLE PRECISION,"+
				"tipo_movimentacao VARCHAR(7) CHECK (tipo_movimentacao IN ('entrada', 'saida')),"+
				"reserva_id SERIAL,"+
				"FOREIGN KEY (reserva_id) REFERENCES reserva(id)"+
			");");
			
			sqls.add("CREATE TABLE IF NOT EXISTS cliente("+
				"id SERIAL PRIMARY KEY,"+
				"fator_risco VARCHAR(45),"+
				"renda_mensal DOUBLE PRECISION,"+
				"pessoa_id SERIAL,"+
				"FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)"+
			");");

			for(String sql:sqls){
				service.executeQuery(sql);
			}
		};
	}

	@Bean
	@Order(2)
	public CommandLineRunner createConta() {
		return (args) -> {
			//var pessoa = new Pessoa();
			//var conta = new Conta();

			//service.save(pessoa);
			//service.save(pessoa, conta);

			//var contaRec = service.getConta(1);
			//service.transfer(conta, contaRec);
//			
			//service.delete(conta.getId());
			//service.delete(contaRec.getId());
		};
	}
}