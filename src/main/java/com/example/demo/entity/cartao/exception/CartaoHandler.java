package com.example.demo.entity.cartao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.entity.cartao.CartaoCredito;

@ControllerAdvice
public class CartaoHandler {
    @ExceptionHandler
    public ResponseEntity<CartaoCredito> handleException(CartaoException e) {
        var cartao = new CartaoCredito();
        return new ResponseEntity<>(cartao, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
