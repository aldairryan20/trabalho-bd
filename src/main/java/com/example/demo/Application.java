package com.example.demo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import com.example.demo.dao.BandeiraCartaoDAO;
import com.example.demo.dao.CartaoCreditoDAO;
import com.example.demo.dao.CategoriaCartaoDAO;
import com.example.demo.dao.ClienteDAO;
import com.example.demo.dao.ContaDAO;
import com.example.demo.dao.PessoaDAO;
import com.example.demo.dao.TipoContaDAO;
import com.example.demo.entity.cartao.BandeiraCartao;
import com.example.demo.entity.cartao.CartaoCredito;
import com.example.demo.entity.cartao.CategoriaCartao;
import com.example.demo.entity.conta.Conta;
import com.example.demo.entity.conta.TipoConta;
import com.example.demo.entity.pessoa.Pessoa;
import com.example.demo.entity.pessoa.PessoaFactory;
import com.example.demo.entity.pessoa.cliente.Cliente;
import com.example.demo.entity.pessoa.cliente.ClienteFactory;
import com.example.demo.service.BdService;

@SpringBootApplication
public class Application {
	@Autowired
	BdService service;
	@Autowired
	PessoaDAO pessoaDAO;
	@Autowired
	ClienteDAO clienteDAO;
	@Autowired
	ContaDAO contaDAO;
	@Autowired
	BandeiraCartaoDAO bandeiraCartaoDAO;
	@Autowired
	CartaoCreditoDAO cartaoCreditoDAO;
	@Autowired
	CategoriaCartaoDAO categoriaCartaoDAO;
	@Autowired
	TipoContaDAO tipoContaDAO;
	@Autowired
	PessoaFactory pessoaFactory;
	@Autowired
	ClienteFactory clienteFactory;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@Order(1)
	public CommandLineRunner createTables() throws SQLException {
		var sqls = new ArrayList<String>();
		return (args) -> {

			sqls.add("CREATE TABLE IF NOT EXISTS bandeira_cartao("+
				"id INT PRIMARY KEY,"+
				"descricao VARCHAR(45)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS categoria_cartao("+
				"id INT PRIMARY KEY,"+
				"descricao VARCHAR(45)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS tipo_boleto("+
				"id INT PRIMARY KEY,"+
				"descricao VARCHAR(45)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS corretor("+
				"id INT PRIMARY KEY,"+
				"nome VARCHAR(45)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS pessoa("+
				"id INT PRIMARY KEY,"+
				"nome VARCHAR(45),"+
				"cpf VARCHAR(45)"+
			");");
			
			sqls.add("CREATE TABLE IF NOT EXISTS tipo_conta("+
				"id INT PRIMARY KEY,"+
				"descricao VARCHAR(45)"+
			");");
			sqls.add("CREATE TABLE IF NOT EXISTS conta("+
				"id INT PRIMARY KEY,"+
				"saldo DOUBLE PRECISION,"+
				"limite_negativo DOUBLE PRECISION,"+
				"tipo_conta_id INT,"+
				"FOREIGN KEY (tipo_conta_id) REFERENCES tipo_conta(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS cartao_credito("+
				"id INT PRIMARY KEY,"+
				"conta_id INT,"+
				"bandeira_id INT,"+
				"categoria_cartao_id INT,"+
				"dt_fechamento VARCHAR(45),"+
				"limite_credito DOUBLE PRECISION,"+
				"FOREIGN KEY (conta_id) REFERENCES conta(id),"+
				"FOREIGN KEY (bandeira_id) REFERENCES bandeira_cartao(id),"+
				"FOREIGN KEY (categoria_cartao_id) REFERENCES categoria_cartao(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS cartao_transacao("+
				"id INT PRIMARY KEY,"+
				"numero_cartao VARCHAR(20),"+
				"cvc VARCHAR(5),"+
				"tipo_cartao VARCHAR(7) CHECK (tipo_cartao IN ('fisico', 'virtual')),"+
				"nome_cartao VARCHAR(255),"+
				"tipo_transacao VARCHAR(7) CHECK (tipo_transacao IN ('credito', 'debito')),"+
				"is_internacional BOOLEAN,"+
				"cartao_credito_id INT,"+
				"bandeira_cartao_id INT,"+
				"FOREIGN KEY (cartao_credito_id) REFERENCES cartao_credito(id),"+
				"FOREIGN KEY (bandeira_cartao_id) REFERENCES bandeira_cartao(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS fatura_cartao("+
				"id INT PRIMARY KEY,"+
				"mes_ref VARCHAR(9),"+
				"ano_ref VARCHAR(4),"+
				"valor DOUBLE PRECISION,"+
				"dt_pagamento TIMESTAMP,"+
				"cartao_credito_id INT,"+
				"FOREIGN KEY (cartao_credito_id) REFERENCES cartao_credito(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS boleto("+
				"id INT PRIMARY KEY,"+
				"valor DOUBLE PRECISION,"+
				"dt_vencimento TIMESTAMP,"+
				"dt_geracao TIMESTAMP,"+
				"codigo_barras VARCHAR(45),"+
				"tipo_boleto_id INT,"+
				"fatura_cartao_id INT,"+
				"FOREIGN KEY (tipo_boleto_id) REFERENCES tipo_boleto(id),"+
				"FOREIGN KEY (fatura_cartao_id) REFERENCES fatura_cartao(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS itens_fatura("+
				"id INT,"+
				"descricao VARCHAR(45),"+
				"fatura_cartao_id INT,"+
				"FOREIGN KEY (fatura_cartao_id) REFERENCES fatura_cartao(id)"+
			");");
			
			sqls.add("CREATE TABLE IF NOT EXISTS compra("+
				"id INT PRIMARY KEY,"+
				"valor DOUBLE PRECISION,"+
				"quantidade_parcela INT,"+
				"taxa_parcelamento DOUBLE PRECISION,"+
				"credor VARCHAR(45),"+
				"data_compra TIMESTAMP,"+
				"corretor_id INT,"+
				"cartao_transacao_id INT,"+
				"FOREIGN KEY (corretor_id) REFERENCES corretor(id),"+
				"FOREIGN KEY (cartao_transacao_id) REFERENCES cartao_transacao(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS pagamento("+
				"id INT PRIMARY KEY,"+
				"valor_total DOUBLE PRECISION,"+
				"dt_pagamento TIMESTAMP,"+
				"valor_parcial DOUBLE PRECISION,"+
				"boleto_id INT,"+
				"fatura_cartao_id INT,"+
				"FOREIGN KEY (fatura_cartao_id) REFERENCES fatura_cartao(id),"+
				"FOREIGN KEY (boleto_id) REFERENCES boleto(id)"+
			");");
			
			sqls.add("CREATE TABLE IF NOT EXISTS movimentacao_cartao("+
				"id INT PRIMARY KEY,"+
				"dt_movimentacao TIMESTAMP,"+
				"valor DOUBLE PRECISION,"+
				"tipo_movimentacao VARCHAR(45),"+
				"cartao_transacao_id INT,"+
				"FOREIGN KEY (cartao_transacao_id) REFERENCES cartao_transacao(id)"+
			");");
			
			sqls.add("CREATE TABLE IF NOT EXISTS movimentacao_conta("+
				"id INT PRIMARY KEY,"+
				"valor DOUBLE PRECISION,"+
				"dt_movimentacao TIMESTAMP,"+
				"tipo_movimentacao VARCHAR(7) CHECK (tipo_movimentacao IN ('entrada', 'saida')),"+
				"conta_id INT,"+
				"FOREIGN KEY (conta_id) REFERENCES conta(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS reserva("+
				"id INT PRIMARY KEY,"+
				"dt_movimentacao TIMESTAMP,"+
				"tipo_movimentacao VARCHAR(10),"+
				"conta_id INT,"+
				"FOREIGN KEY (conta_id) REFERENCES conta(id)"+
			");");

			sqls.add("CREATE TABLE IF NOT EXISTS movimentacao_reserva("+
				"id INT PRIMARY KEY,"+
				"dt_movimentacao TIMESTAMP,"+
				"valor DOUBLE PRECISION,"+
				"tipo_movimentacao VARCHAR(7) CHECK (tipo_movimentacao IN ('entrada', 'saida')),"+
				"reserva_id INT,"+
				"FOREIGN KEY (reserva_id) REFERENCES reserva(id)"+
			");");
			
			sqls.add("CREATE TABLE IF NOT EXISTS cliente("+
				"id INT PRIMARY KEY,"+
				"fator_risco VARCHAR(45),"+
				"renda_mensal DOUBLE PRECISION,"+
				"cartao_credito_id INT,"+
				"pessoa_id INT,"+
				"FOREIGN KEY (cartao_credito_id) REFERENCES cartao_credito(id),"+
				"FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)"+
			");");

			for(String sql:sqls){
				service.executeQuery(sql);
			}
		};
	}

	@Bean
	@Order(2)
	public CommandLineRunner criandoConta() {
		return (args) -> {
			int id = 0;

			var pessoa = new Pessoa();
			pessoa.setNome("Nome");
			pessoa.setId(id);
			pessoa.setCpf("12345678900");

			var cliente = new Cliente();
			cliente.setId(pessoa.getId());
			cliente.setNome(pessoa.getNome());
			cliente.setCpf(pessoa.getCpf());

			cliente.setFatorRisco("Fator risco");
			cliente.setFaturas(null);
			cliente.setRendaMensal(10000.0);

			var cartao = new CartaoCredito();
			cartao.setId(id);
			cartao.setLimiteCredito(10000.0);

			var calendar = Calendar.getInstance();
			calendar.setTime(new java.util.Date());
			calendar.add(Calendar.MONTH, 1);
			cartao.setDataFechamento(new Date(calendar.getTimeInMillis()));

			var bandeira = new BandeiraCartao();
			bandeira.setId(cartao.getId());
			bandeira.setDescricao("descricao bandeira");

			cartao.setBandeira(bandeira);

			var catCartao = new CategoriaCartao();
			catCartao.setDescricao("descricao catCartao");
			catCartao.setId(cartao.getId());

			cartao.setCatCartaoId(catCartao.getId());

			cartao.setBandeira(bandeira);
			cliente.setCartao(cartao);

			var conta = new Conta();
			conta.setCartao(cartao);
			conta.setId(cliente.getId());
			conta.setLimiteNegativo(cartao.getLimiteCredito());
			conta.setSaldo(0);

			cartao.setContaId(conta.getId());
			cliente.setCartao(cartao);

			var tipoConta = new TipoConta();
			tipoConta.setDescricao("descricao");
			tipoConta.setId(conta.getId());
			conta.setTipoContaId(tipoConta.getId());

			// clienteDAO.delete(cliente.getId());		// PK - cartao - pessoa
			// cartaoCreditoDAO.delete(cartao.getId());	// PK - conta - bandeira - catCartao.
			// contaDAO.delete(conta.getId());		// PK - tipoConta
			// tipoContaDAO.delete(tipoConta.getId());
			// bandeiraCartaoDAO.delete(bandeira.getId());
			// categoriaCartaoDAO.delete(catCartao.getId());
			// pessoaDAO.delete(pessoa.getId());

			// 1) pessoa
			pessoaDAO.insertPessoa(pessoa.getCpf(), pessoa.getId(), pessoa.getNome());

			// 2) catCartao
			categoriaCartaoDAO.insertCategoriaCartao(catCartao.getId(), catCartao.getDescricao());

			// 2) bandeira
			bandeiraCartaoDAO.insertBandeiraCartao(bandeira.getId(), bandeira.getDescricao());

			// 3) tipoConta
			tipoContaDAO.insertTipoConta(tipoConta.getId(), tipoConta.getDescricao());

			// 4) conta
			contaDAO.insertConta(conta.getId(), conta.getSaldo(), conta.getLimiteNegativo(), conta.getTipoContaId());

			// 5) cartao
			cartaoCreditoDAO.insertCartaoCredito(cartao.getId(), cartao.getDataFechamento(), cartao.getContaId(), cartao.getCatCartaoId(), cartao.getLimiteCredito(), cartao.getBandeira().getId());
			
			// 6) cliente
			clienteDAO.insertCliente(0, cliente.getFatorRisco(), cliente.getRendaMensal(), cliente.getCartao().getId());
			
		};
	}
	@Bean
	@Order(3)
	public CommandLineRunner comprandoComCartao() {
		return args -> {
			var cliente = clienteDAO.findById(0);
			cliente.loadFaturas();
			var cartao = cartaoCreditoDAO.findById(0);

			System.out.println(cliente);

			cliente.setCartao(cartao);
			cliente.comprar(5123);
			cliente.comprar(5123);
			
		};
	}

}