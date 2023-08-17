package com.techlead.javaspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/")
    public ResponseEntity<?> getHome() {
        return ResponseEntity.ok("Home");
    }
}
