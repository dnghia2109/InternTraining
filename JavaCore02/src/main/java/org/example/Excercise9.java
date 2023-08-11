package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Bài tập 9: Đếm số lần xuất hiện của các từ trong một văn bản.
// Hãy viết một chương trình Java để đọc một đoạn văn bản từ người dùng
// và đếm số lần xuất hiện của mỗi từ trong văn bản.
// Sử dụng HashMap để lưu trữ từ làm key và số lần xuất hiện làm value.
// Sau đó, hiển thị danh sách các từ và số lần xuất hiện tương ứng của chúng.
public class Excercise9 {
    public static void main(String[] args) {
        String doc = "Toi ten la Lai Duy Nghia, Lai Duy Nghia sinh nam 2001, het";
        String[] stringArr = doc.toLowerCase().trim().split("[\\s,.]+");
        System.out.println(Arrays.toString(stringArr));

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word:stringArr) {
            if (wordCount.containsKey(word)) {
                wordCount.put(word, wordCount.get(word) + 1);
            } else {
                wordCount.put(word, 1);
            }
        }
        System.out.println(wordCount);
    }
}
