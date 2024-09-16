/*
package com.example.demo.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ContaDAO;
import com.example.demo.entity.conta.Conta;
import com.example.demo.service.BdService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/api")
public class ContaController {
    @Autowired
    ContaDAO contaDAO;
    BdService service;
    Logger logger = LogManager.getLogger(getClass());

    @RequestMapping(value = "/contas", method = RequestMethod.POST)
    public ResponseEntity<String> insertConta(@RequestBody Conta conta) {
        contaDAO.insertConta(conta.getId(), conta.getSaldo(), conta.getLimiteNegativo(), conta.getTipoContaId());
        var responseBody = "created";
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    //@SuppressWarnings("unchecked")
    //@RequestMapping(value = "/contas", method=RequestMethod.GET)
    //public String findAllContasAsJson() {
        //String methodName = new Throwable().getStackTrace()[0].getMethodName();

        //ArrayList<Conta> contas = contaDAO.findAll();
        //var objectMapper = new ObjectMapper();
        //var jsonResult = "";
        //try{
            //jsonResult = objectMapper.writeValueAsString(contas);
        //} catch(Exception e){
            //logger.error("Error at "+getClass().getName()+"\nMethod = "+methodName+"\n", e.getMessage());
        //}
        //return jsonResult;
    //}

    @RequestMapping(value = "/contas/{id}", method = RequestMethod.GET)
    public String findContaByIdAsJson(int id) {
        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        
        var conta = contaDAO.findById(id);
        var objectMapper = new ObjectMapper();
        var jsonResult = "";
        try{
            jsonResult = objectMapper.writeValueAsString(conta);
        } catch(Exception e){
            logger.error("Error at "+getClass().getName()+"\nMethod = "+methodName+"\n", e.getMessage());
        }
        return jsonResult;
    }
}
*/