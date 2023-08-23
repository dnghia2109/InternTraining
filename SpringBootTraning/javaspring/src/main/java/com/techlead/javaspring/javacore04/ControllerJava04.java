package com.techlead.javaspring.javacore04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/java04")
public class ControllerJava04 {

    @Autowired
    private WorksheetService worksheetService;

    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees() throws IOException {
//        worksheetService.getData("/Users/nghialai/Desktop/TechLead/InternTraining/JavaCore04_excel/src/main/resources/BangCong.xlsx");
//        file.getOriginalFilename();
        return ResponseEntity.ok(worksheetService.getData("/Users/nghialai/Desktop/TechLead/InternTraining/JavaCore04_excel/src/main/resources/BangCong.xlsx"));
    }
}
