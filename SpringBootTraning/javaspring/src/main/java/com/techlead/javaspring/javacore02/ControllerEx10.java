package com.techlead.javaspring.javacore02;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/java02/ex10")
public class ControllerEx10 {
    Map<String, Double> listPoints = new HashMap<>();

    @PostMapping("/add-student")
    public String addScore(@RequestParam("name") String name, @RequestParam("score") Double score) {
        listPoints.put(name, score);
        return "Add student success";
    }

    @GetMapping("/high-score")
    public int countHighScore() {
        int count = 0;
        for (Map.Entry<String, Double> entry : listPoints.entrySet()) {
            if (entry.getValue() >= 8) {
                count++;
            }
        }
        return count;
    }

    @GetMapping("/normal-score")
    public int countNormalScore() {
        int count = 0;
        for (Map.Entry<String, Double> entry : listPoints.entrySet()) {
            if (entry.getValue() >= 5 && entry.getValue() < 8) {
                count++;
            }
        }
        return count;
    }

    @GetMapping("/low-score")
    public int countLowScore() {
        int count = 0;
        for (Map.Entry<String, Double> entry : listPoints.entrySet()) {
            if (entry.getValue() < 5) {
                count++;
            }
        }
        return count;
    }
}
