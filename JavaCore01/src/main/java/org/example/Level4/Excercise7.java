package org.example.Level4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Write a program that takes a list of strings as input and returns the list sorted by the number of
// distinct words in each string, with the longest strings appearing first.
// (Khuyến khích dùng forEach với javascript)
// Sắp xếp từ chuỗi dài nhất đến ngắn nhất, trong trường hợp có 2 chuỗi cùng độ dài thì sắp xếp theo thứ tự bảng chữ cái
// Ví dụ: ['the quick brown fox', 'the lazy dog jumps over the fence', 'the cat in the hat']
// Kết quả: ['the lazy dog jumps over the fence', 'the quick brown fox', 'the cat in the hat']
public class Excercise7 {
    public static void main(String[] args) {
        String[] arr = {"the quick brown fox", "the lazy dog jumps over the fence",
                "the cat in the hat", "the cat in the bat"};
        sapXep(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sapXep(String[] arr) {
        Arrays.sort(arr, (a, b) -> {
            int distinctWordsA = countDistinctWords(a);
            int distinctWordsB = countDistinctWords(b);

            if (a.length() == b.length()) {
                return a.compareTo(b);
            }
            return distinctWordsB - distinctWordsA;
        });
    }

    public static int countDistinctWords(String str) {
        String[] words = str.split(" "); // Tách chuỗi
        return (int) Arrays.stream(words).distinct().count();
    }
}
