package com.example.demo.rest;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cliente;
import com.example.demo.services.Service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ClienteController {
    Service service;

    //@RequestMapping(value = "/clientes", method=RequestMethod.GET)
    //public String getAll(){
    //    List<Cliente> clientes;
    //    return clientes;
    //}

    //@RequestMapping(value = "/clientes", method=RequestMethod.POST)
    //public String add(@PathVariable String param) {
    //    return new String();
    //}

    @RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable int id) {
        String sql = "SELECT * FROM CONTAS WHERE id = ?";
        try {
            Map<String, Object> result = service.jdbcTemplate.queryForMap(sql, id);
            return ResponseEntity.ok(result);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
    }
}
