package com.example.demo.rest;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.PessoaDAO;
import com.example.demo.entity.pessoa.Pessoa;
import com.example.demo.service.BdService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class PessoaController {
    @Autowired
    PessoaDAO pessoaDAO;
    BdService service;

    @RequestMapping(value = "/pessoas", method = RequestMethod.POST)
    public ResponseEntity<String> insertPessoa(@RequestBody Pessoa pessoa) {
        pessoaDAO.insertPessoa(pessoa.getCpf(), pessoa.getId(), pessoa.getNome());
        var responseBody = "created";
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/pessoas", method = RequestMethod.GET)
    public ResponseEntity<String> findAllPessoasAsJson() {
        
        HashMap<Integer, String> pessoas = pessoaDAO.findAll();
        var objectMapper = new ObjectMapper();
        var jsonResult = "";
        try{
            jsonResult = objectMapper.writeValueAsString(pessoas);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<String>(jsonResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/pessoas/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> findPessoaById(@PathVariable int id) {
        var pessoa = pessoaDAO.findPessoaById(id);
        if (pessoa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pessoa, HttpStatus.FOUND);
    }
}
