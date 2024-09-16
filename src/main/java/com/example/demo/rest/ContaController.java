package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ContaDAO;
import com.example.demo.entity.conta.Conta;


@RestController
@RequestMapping("/api")
public class ContaController {
    @Autowired
    private ContaDAO contaDAO;

    @GetMapping("/contas")
    public ResponseEntity<List<Conta>> findAll() {
        var contas = contaDAO.findAll();
        return ResponseEntity.ok(contas);
    }

    @RequestMapping(value = "/contas/{id}", method = RequestMethod.GET)
    public ResponseEntity<Conta> findById(@PathVariable int id) {
        var conta = contaDAO.findById(id);
        if (conta == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(conta, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/contas", method = RequestMethod.POST)
    public ResponseEntity<Conta> insertConta(@RequestBody Conta conta) {
        contaDAO.insertConta(conta.getId(), conta.getSaldo(), conta.getLimiteNegativo(), conta.getTipoContaId());
        return new ResponseEntity<>(conta, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/contas", method = RequestMethod.PUT)
    public ResponseEntity<Conta> update(@RequestBody Conta conta) {
        contaDAO.update(conta.getId(), conta.getSaldo(), conta.getLimiteNegativo(), conta.getTipoContaId());
        
        return new ResponseEntity<>(conta, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/contas/{id}", method = RequestMethod.DELETE)
public ResponseEntity<String> delete(@PathVariable int id) {
    try {
        contaDAO.delete(id);
        var responseBody = "deleted";
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
