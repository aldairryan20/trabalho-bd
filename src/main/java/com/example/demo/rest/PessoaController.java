package com.example.demo.rest;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class PessoaController {
    private static final Logger logger = LogManager.getLogger(PessoaController.class);

    @Autowired
    private PessoaDAO pessoaDAO;

    @GetMapping("/pessoas")
    public ResponseEntity<String> findAllPessoasAsJson() {
        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        var pessoas = pessoaDAO.findAll();
        var objectMapper = new ObjectMapper();
        var jsonResult = "";

        try {
            jsonResult = objectMapper.writeValueAsString(pessoas);
        } catch (Exception e) {
            logger.error("Error at " + getClass().getName() + "\nMethod = " + methodName + "\n", e);
        }

        return ResponseEntity.ok(jsonResult);
    }

    @RequestMapping(value = "/pessoas", method = RequestMethod.POST)
    public ResponseEntity<String> insertPessoa(@RequestBody Pessoa pessoa) {
        pessoaDAO.insertPessoa(pessoa.getCpf(), pessoa.getId(), pessoa.getNome());
        var responseBody = "created";
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/pessoas/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> findPessoaById(@PathVariable int id) {
        var pessoa = pessoaDAO.findById(id);
        if (pessoa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pessoa, HttpStatus.FOUND);
    }
}
