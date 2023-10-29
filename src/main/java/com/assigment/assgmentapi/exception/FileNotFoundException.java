package com.assigment.assgmentapi.exception;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String message){
        super(message);
    }
    
    public FileNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
