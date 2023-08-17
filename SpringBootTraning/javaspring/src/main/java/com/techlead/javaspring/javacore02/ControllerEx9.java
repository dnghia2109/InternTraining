package com.techlead.javaspring.javacore02;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/java02/ex9")
public class ControllerEx9 {
// Bài tập 9: Đếm số lần xuất hiện của các từ trong một văn bản.
// Hãy viết một chương trình Java để đọc một đoạn văn bản từ người dùng
// và đếm số lần xuất hiện của mỗi từ trong văn bản.
// Sử dụng HashMap để lưu trữ từ làm key và số lần xuất hiện làm value.
// Sau đó, hiển thị danh sách các từ và số lần xuất hiện tương ứng của chúng.

    @GetMapping("")
    public ResponseEntity<?> cau9(@RequestParam String doc) {
        String[] stringArr = doc.toLowerCase().trim().split("[\\s,.]+");

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word:stringArr) {
            if (wordCount.containsKey(word)) {
                wordCount.put(word, wordCount.get(word) + 1);
            } else {
                wordCount.put(word, 1);
            }
        }

        return ResponseEntity.ok(wordCount);
    }
}
