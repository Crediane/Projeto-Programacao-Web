package com.api.turmas.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(NotFoundException ex){
        Map<String,Object> body = new HashMap<>();
        body.put("status",404);
        body.put("error","Not Found");
        body.put("message",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String,Object>> handleBadRequest(BadRequestException ex){
        Map<String,Object> body = new HashMap<>();
        body.put("status",400);
        body.put("error","Bad Request");
        body.put("message",ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex){
        Map<String,Object> body = new HashMap<>();
        body.put("status",400);
        body.put("error","Bad Request");
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err -> {
            String field=((FieldError) err).getField();
            String msg=err.getDefaultMessage();
            errors.put(field,msg);
        });
        body.put("validationErrors",errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
