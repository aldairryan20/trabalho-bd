package com.example.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ContaDAO;
import com.example.demo.entity.conta.Conta;
import com.example.demo.service.MyService;


@RestController
@RequestMapping("/api")
public class ContaController {
    ContaDAO contaDAO;
    MyService service;

    @RequestMapping(value = "/contas/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable int id) {
        return service.findByIdAsJson(id);
    }

    @RequestMapping(value = "/contas", method=RequestMethod.GET)
    public String findAll(){
        return service.findAllContasAsJson();
    }

    @RequestMapping(value = "/contas", method = RequestMethod.POST)
    public ResponseEntity<String> insertConta(@RequestBody Conta conta) {
        contaDAO.insertConta(conta.getId(), conta.getSaldo(), conta.getLimiteNegativo(), conta.getTipoContaId());
        var responseBody = "created";
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }
}
