package com.example.demo.rest.exception;

//import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus
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
