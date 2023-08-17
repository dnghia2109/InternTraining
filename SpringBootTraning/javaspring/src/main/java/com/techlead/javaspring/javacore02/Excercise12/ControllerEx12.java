package com.techlead.javaspring.javacore02.Excercise12;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/java02/ex12")
@AllArgsConstructor
public class ControllerEx12 {
    private Excercise12 excercise12;

    @GetMapping("/sort")
    public ResponseEntity<?> sortListProduct(@RequestParam (required = false, defaultValue = "") String type) {
        return switch (type) {
            case "name" -> ResponseEntity.ok(excercise12.sortByName());
            case "price" -> ResponseEntity.ok(excercise12.sortByPrice());
            case "date" -> ResponseEntity.ok(excercise12.sortByDate());
            case "price-date" -> ResponseEntity.ok(excercise12.sortByPriceAndDate());
            default -> ResponseEntity.ok(excercise12.getAllProduct());
        };
    }
}
