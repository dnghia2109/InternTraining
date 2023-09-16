package org.example.Level4;

import java.util.ArrayList;
import java.util.List;

// Write a program that takes a list of strings as input and returns the length of the longest substring
// that appears in every string in the list.
//VD:
//["abcdefg", "abcde", "abcdef", "ab", "abc"] => 2
//["programming", "programmer", "program"] => 7
//["hello", "world", "jelly"] => 1
//["abcd", "ab", "abcd", "ab", "abcd"] => 2
public class Excercise3 {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>(List.of("bcdefg", "abcde", "abcdef", "abcd", "abc"));
        // tim chuoi ngan nhat
        String minStr = stringList.get(0);
        for (String str : stringList) {
            if (str.length() < minStr.length()) {
                minStr = str;
            }
        }
        // tim do dai chuoi con chung dai nhat
        int maxLength = 0;
        for (int i = 0; i < minStr.length(); i++) {
            for (int j = i + 1; j <= minStr.length(); j++) {
                String subString = minStr.substring(i, j);
                boolean findString = true;
                for (String s : stringList) {
                    if (!s.contains(subString)) {
                        findString = false;
                        break;
                    }
                }
                if (findString) {
                    maxLength = Math.max(maxLength, subString.length());
                }
            }
        }
        System.out.println(maxLength);
    }
}
