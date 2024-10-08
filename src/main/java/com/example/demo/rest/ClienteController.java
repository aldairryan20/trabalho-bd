package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dao.ClienteDAO;
import com.example.demo.entity.pessoa.cliente.Cliente;
import com.example.demo.rest.exception.NotFoundException;
import com.example.demo.entity.compra.fatura.ItensFatura;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClienteController {
    @Autowired
    private ClienteDAO clienteDAO;
    
    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity.ok(clienteDAO.findAll());
    }

    @RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> findById(@PathVariable int id) {
        ArrayList<Cliente> clientes = clienteDAO.findAll();
        if (id >= clientes.size() || id < 0) {
            throw new NotFoundException("Cliente not found - "+id);
        }
        var cliente = clienteDAO.findById(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @RequestMapping(value = "/clientes", method = RequestMethod.POST)
    public ResponseEntity<Cliente> insertCliente(@RequestBody Cliente cliente) {
        ArrayList<Cliente> clientes = clienteDAO.findAll();
        clienteDAO.insertCliente(cliente.getId(), cliente.getFatorRisco(), cliente.getRendaMensal(), cliente.getCartao().getId());
        clientes.add(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/clientes", method = RequestMethod.PUT)
    public ResponseEntity<Cliente> update(@RequestBody Cliente cliente) {
        clienteDAO.updateCliente(cliente.getId(), cliente.getFatorRisco(), cliente.getRendaMensal(), cliente.getCartao().getId());
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/clientes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            clienteDAO.delete(id);
            var responseBody = "deleted";
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clientes/{id}/fatura")
    public ResponseEntity<Double> getFatura(@PathVariable int id) {
        var cliente = clienteDAO.findById(id);
        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        double valorFatura = cliente.verificarFaturaAtual();
        return new ResponseEntity<>(valorFatura, HttpStatus.OK);
    }

    @GetMapping("/clientes/{id}/itens-fatura")
    public ResponseEntity<List<ItensFatura>> getItensFatura(@PathVariable int id) {
        var cliente = clienteDAO.findById(id);
        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ItensFatura> itensFatura = clienteDAO.findItensFaturaByClienteId(id);
        return new ResponseEntity<>(itensFatura, HttpStatus.OK);
    }
}
