package com.example.demo.rest;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.PessoaDAO;
import com.example.demo.entity.pessoa.Pessoa;
import com.example.demo.service.MyService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class PessoaController {
    @Autowired
    PessoaDAO pessoaDAO;
    MyService service;

    @RequestMapping(value = "/pessoas", method = RequestMethod.POST)
    public ResponseEntity<String> insertPessoa(String cpf, int id, String nome) {
        var pessoa = new Pessoa();
        pessoa.setCpf(cpf);
        pessoa.setId(id);
        pessoa.setNome(nome);

        var sql = "INSERT AT pessoa(cpf, id, nome) "+
        "VALUES("+cpf+", "+id+", "+nome+");";
        service.executeQuery(sql);
        var responseBody = "created";
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/pessoas", method = RequestMethod.GET)
    public ResponseEntity<String> findAllPessoasAsJson() {
        
        HashMap<Integer, Pessoa> pessoas = pessoaDAO.findAll();
        var objectMapper = new ObjectMapper();
        var jsonResult = "";
        try{
            jsonResult = objectMapper.writeValueAsString(pessoas);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<String>(jsonResult, HttpStatus.OK);
    }
}
