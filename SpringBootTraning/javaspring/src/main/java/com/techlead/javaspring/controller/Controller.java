package com.techlead.javaspring.controller;

import com.techlead.javaspring.entity.Role;
import com.techlead.javaspring.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/roles")
    public ResponseEntity<?> getHome1() {
        List<Role> rs = roleRepository.findAll();
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/")
    public ResponseEntity<?> getHome() {
        return ResponseEntity.ok("Home");
    }
}
