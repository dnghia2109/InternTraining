package com.techlead.javaspring.javacore01;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Level4_Ex7Service {
    public void sapXep(String[] arr) {
        Arrays.sort(arr, (a, b) -> {
            int distinctWordsA = countDistinctWords(a);
            int distinctWordsB = countDistinctWords(b);

            if (a.length() == b.length()) {
                return a.compareTo(b);
            }
            return distinctWordsB - distinctWordsA;
        });
    }

    public  int countDistinctWords(String str) {
        String[] words = str.split(" "); // tach chuoi thanh mang
        return (int) Arrays.stream(words).distinct().count();
    }
}
