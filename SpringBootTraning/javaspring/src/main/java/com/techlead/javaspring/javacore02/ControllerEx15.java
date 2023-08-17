package com.techlead.javaspring.javacore02;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/v1/java02/ex15")
public class ControllerEx15 {
    @GetMapping("")
    public ResponseEntity<?> cau15(@RequestParam Set<Integer> nums, @RequestParam Set<Integer> nums1) {
        Set<Integer> result = new HashSet<>();
        for (int num: nums) {
            result.add(num);
        }
        for (int num1: nums1) {
            result.add(num1);
        }
        return ResponseEntity.ok(result);
    }
}
