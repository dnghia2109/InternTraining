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


    public static void main(String[] args) {
        String str = "A man a Panama plan a canal";
        String newStr = str.replace(" ", "").toLowerCase();//xóa space, lowercase chuỗi
        //! luu so lan xuat hien tung ky tu
        int countChar[] = new int[26];
        for (char c : newStr.toCharArray()) {
            countChar[c - 'a']++;
        }
        //! xac dinh palindrome
        int palindrome = 0;
        boolean oddCount = false;
        for (int count : countChar) {
            if (count % 2 == 0) {
                palindrome += count;
            } else {
                palindrome += count - 1; // nếu kí tự xuất hiên lẻ thì - 1 -> chẵn
                oddCount = true;
            }
        }
        if (oddCount) {
            palindrome += 1; // nếu tồn tại ít nhất 1 kí tự lẻ thì cho phép +1 kí tự
        }
        System.out.println(palindrome);
    }
}
