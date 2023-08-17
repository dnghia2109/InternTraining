package com.techlead.javaspring.javacore02;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("api/v1/java02/ex14")
public class ControllerEx14 {
    @GetMapping("")
    public ResponseEntity<?> cau14(@RequestParam Set<Integer> nums, @RequestParam Set<Integer> nums1) {
        Set<Integer> result = new HashSet<>();
        for (int num: nums) {
            for (int num1: nums1) {
                if (num == num1) {
                    result.add(num);
                }
            }
        }
        return ResponseEntity.ok(result);
    }
}
