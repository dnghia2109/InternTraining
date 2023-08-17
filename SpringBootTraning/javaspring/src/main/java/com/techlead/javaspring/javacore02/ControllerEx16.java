package com.techlead.javaspring.javacore02;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/v1/java02/ex16")
public class ControllerEx16 {
    @GetMapping("")
    public ResponseEntity<?> cau16 (@RequestParam Set<Integer> nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        return ResponseEntity.ok("- Max: " + max + "\n- Min: " + min);
    }
}
