package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> helloWorld(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hello World!!!");
    }
}
