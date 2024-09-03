package com.example.demo.rest;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cliente;
import com.example.demo.services.Service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/api")
public class ClienteController {
    Service service;

    @RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
    public Cliente getById(@PathVariable int id) {
        return service.findById(id);
    }
}
