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

import com.example.demo.dao.PessoaDAO;
import com.example.demo.entity.pessoa.Pessoa;

@RestController
@RequestMapping("/api")
public class PessoaController {

    @Autowired
    private PessoaDAO pessoaDAO;

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> findAll() {
        var pessoas = pessoaDAO.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @RequestMapping(value = "/pessoas/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> findById(@PathVariable int id) {
        var pessoa = pessoaDAO.findById(id);
        if (pessoa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pessoa, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/pessoas", method = RequestMethod.POST)
    public ResponseEntity<Pessoa> insertPessoa(@RequestBody Pessoa pessoa) {
        pessoaDAO.insertPessoa(pessoa.getCpf(), pessoa.getId(), pessoa.getNome());
        return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/pessoas", method = RequestMethod.PUT)
    public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa) {
        pessoaDAO.update(pessoa.getNome(), pessoa.getCpf(), pessoa.getId());
        
        return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/pessoas/{id}", method = RequestMethod.DELETE)
public ResponseEntity<String> delete(@PathVariable int id) {
    try {
        pessoaDAO.delete(id);
        var responseBody = "deleted";
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
