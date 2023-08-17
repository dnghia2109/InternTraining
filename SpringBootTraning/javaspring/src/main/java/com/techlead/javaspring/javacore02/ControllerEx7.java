package com.techlead.javaspring.javacore02;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/java02/ex7")
public class ControllerEx7 {
    Map<String, List<Double>> studentListPoint = new HashMap<>();
    @GetMapping("/show-member")
    public ResponseEntity<?> getAllMember() {
        return ResponseEntity.ok(studentListPoint);
    }

    @GetMapping("/avg-point")
    public ResponseEntity<?> getListAvgPoint() {
        Map<String, Double> avgPoints = new HashMap<>();
        for (String stu : studentListPoint.keySet()) {
            List<Double> points = studentListPoint.get(stu);
            double pointAvg = 0;
            for (double point : points) {
                pointAvg += point;
            }
            pointAvg /= points.size();
            avgPoints.put(stu, pointAvg);
        }
        return ResponseEntity.ok(avgPoints);
    }
}
