package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import com.example.demo.entity.conta.Conta;
import com.example.demo.services.movimentacao.MovimentacaoConta;

@Service
public class ContaService {
    private static final String URL = "jdbc:postgresql://localhost:5432/database";
    private static final String USER = "postgres";
    private static final String PASSWORD = "sua_senha";

    private HashMap<Integer, String> hashMap = new HashMap<>();

    @PersistenceContext
    private EntityManager entityManager;

    public String getMovimentacao(MovimentacaoConta movimentacaoConta) {
        return movimentacaoConta.toString();
    }

    public double getSaldo(Conta conta){
        return conta.getSaldo();
    }

    public void transferencia(Conta contaA, Conta contaB, double valor) {
        contaA.setSaldo(contaA.getSaldo() - valor);
        contaB.setSaldo(contaB.getSaldo() + valor);
        System.out.println("Tranferência realizada em "+ LocalDateTime.now() +"\n"+
                           "Valor: "+ valor +"\n"+
                           "De"+ findById(contaA.getClienteId()).getNome((contaA.getId()), hashMap) +"\n"+
                           "Para"+ findById(contaB.getClienteId()).getNome((contaA.getId()), hashMap));
    }

    public Conta findById(int clienteId) {
        TypedQuery<Conta> query = entityManager.createQuery("SELECT c FROM Conta c WHERE c.id = :clienteId", Conta.class);
        query.setParameter("clienteId", clienteId);
        return query.getSingleResult();
    }

    public List<Conta> findAll() {
        TypedQuery<Conta> theQuery = entityManager.createQuery("from Conta", Conta.class);
        return theQuery.getResultList();
    }
}
