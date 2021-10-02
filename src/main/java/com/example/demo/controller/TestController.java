package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> helloWorld(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hello World!!!");
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> helloAdmin(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hello Admin!!!");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> helloUser(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hello User!!!");
    }
}
