package com.techlead.javaspring.javacore02;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/java02/ex13")
public class ControllerEx13 {
    @GetMapping("")
    public ResponseEntity<?> cau13 (@RequestParam List<Integer> nums) {
        Set<Integer> result = new HashSet<>();
        Set<Integer> uniqueNums = new HashSet<>();

        for (int num : nums) {
            if (!uniqueNums.add(num)) {
                result.add(num);
            }
        }
        return ResponseEntity.ok("Danh sách các số bị lặp: " + result);
    }
}
