package com.example.demo.rest.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String msg) {
        super(msg);
    }
    
    public NotFoundException(Throwable t) {
        super(t);
    }
    
    public NotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
    
}
