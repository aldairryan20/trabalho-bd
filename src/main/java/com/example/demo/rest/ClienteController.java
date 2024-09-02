package com.example.demo.rest;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.conta.Conta;
import com.example.demo.services.ContaService;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class ClienteController {
    ContaService service;
    
    @RequestMapping(value = "/clientes", method=RequestMethod.GET)
    public List<Conta> clientes() {
        var listaClientes = service.findAll();
        return listaClientes;
    }
    
}
