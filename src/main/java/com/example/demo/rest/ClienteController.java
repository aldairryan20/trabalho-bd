package com.example.demo.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MyService;

@RestController
@RequestMapping("/api")
public class ClienteController {
    MyService service;

    @RequestMapping(value = "/contas/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable int id) {
        return service.findByIdAsJson(id);
    }

    @RequestMapping(value = "/contas", method=RequestMethod.GET)
    public String findAll(){
        return service.findAllContasAsJson();
    }
    
}
