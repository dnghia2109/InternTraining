package org.example.Level3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Excercise7 {
    // Write a program that takes a string as input and
    // returns the length of the longest palindrome that can be formed by rearranging the characters in the string.
    // Giải thích : palindrome	: Chuỗi ký tự mà đọc xuôi hay ngược đều giống nhau, vd: aba, uwu
    // VD: “A man a plan a canal Panama” 👉 21 (amanaplanacanalpanama)
    //     xxabcxycbaxx
    // chan lay het
    // le >3  -1  = chan
    // + 1 lẻ => thành tâm đối xứng


//    public static void main(String[] args) {
////        char charr = 'a';
////        System.out.println((int)charr);
//        String str = "A man a Panama plan a canal";
//        String newStr = str.replace(" ", "").toLowerCase();//xóa space, lowercase chuỗi
//        //Số lần xuất hiện của mỗi kí tự
//        int countChar[] = new int[26];
//        for (char c : newStr.toCharArray()) {
//            countChar[c - 'a']++;
//        }
//
//
//        int palindrome = 0;
//        boolean oddCount = false;
//        for (int count : countChar) {
//            if (count % 2 == 0) {
//                palindrome += count;
//            } else {
//                palindrome += count - 1; // nếu kí tự xuất hiên lẻ thì - 1 -> chẵn
//                oddCount = true;
//            }
//        }
//        if (oddCount) {
//            palindrome += 1; // nếu tồn tại kí tự lẻ thì cho phép +1 kí tự
//        }
//        System.out.println(palindrome);
//    }
public static void main(String[] args) {
    String input = "121";
    int palindromeCount = findLongestPalindromeCount(input);
    System.out.println("Longest palindrome count: " + palindromeCount);
}

    public static int findLongestPalindromeCount(String input) {
        int palindromeCount = 0;
        boolean oddCount = false;
        if (input.length() == 0) {
            return palindromeCount;
        }
        char[] charArray = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().toCharArray();

        int[] charCount = new int[36]; // 26 chữ cái, 10 số
        for (char c : charArray) {
            if (Character.isLetter(c)) {
                charCount[c - 'a']++;
            } else if (Character.isDigit(c)) {
                charCount[c - '0' + 26]++;
            }
        }

        for (int count : charCount) {
            if (count % 2 == 0) {
                palindromeCount += count;
            } else {
                palindromeCount += count - 1;
                oddCount = true;
            }
        }

        if (oddCount) {
            palindromeCount += 1;
        }

        return palindromeCount;
    }
}
