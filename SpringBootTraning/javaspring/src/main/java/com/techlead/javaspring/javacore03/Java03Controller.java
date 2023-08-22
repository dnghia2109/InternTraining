package com.techlead.javaspring.javacore03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/java03")
public class Java03Controller {
    @Autowired
    private Java03Service java03Service;

    @GetMapping("/ex1")
    public ResponseEntity<?> java03(@RequestParam String infixString) {
        String postfix = java03Service.chuyenTrungToSangHauTo(infixString);
        return ResponseEntity.ok("- Infix: " + infixString + "\n- Postfix: " +
                 postfix + "\n- Giá trị :" + java03Service.tinhGiaTri(postfix));
    }
}
