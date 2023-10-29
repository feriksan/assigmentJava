package com.assigment.assgmentapi.controllers;
import com.assigment.assgmentapi.exception.ErrorResponse;
import com.assigment.assgmentapi.exception.UserException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestControllerAdvice
@RequestMapping("auth")
@RequiredArgsConstructor
public class RestControllerAdvice {
    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<ErrorResponse> handleSomeException(UserException e) {
        
        ErrorResponse response = new ErrorResponse();
        response.setErrorMsg(e.getMessage());
        response.setErrorCode("103");
        response.setData(null);
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleSomeException(BadCredentialsException e) {
        
        ErrorResponse response = new ErrorResponse();
        response.setErrorMsg(e.getMessage());
        response.setErrorCode("102");
        response.setData(null);
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleSomeException() {
        
        ErrorResponse response = new ErrorResponse();
        response.setErrorMsg("Token tidak tidak valid atau kadaluwarsa");
        response.setErrorCode("108");
        response.setData(null);
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
