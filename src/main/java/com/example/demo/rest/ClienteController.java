package com.example.demo.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class ClienteController {
    @RequestMapping(value = "/clientes", method=RequestMethod.GET)
    public String clientes(){
        return "";
    }
    
}
