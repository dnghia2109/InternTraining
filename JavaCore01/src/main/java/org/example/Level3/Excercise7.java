package org.example.Level3;

import java.util.Scanner;

public class Excercise7 {
    // Write a program that takes a string as input and
    // returns the length of the longest palindrome that can be formed by rearranging the characters in the string.
    // Giải thích : palindrome	: Chuỗi ký tự mà đọc xuôi hay ngược đều giống nhau, vd: aba, uwu
    // VD: “A man a plan a canal Panama” 👉 21 (amanaplanacanalpanama)

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("Enter a string: ");
//        String input = sc.nextLine();
//
//        int longestPalindromeLength = findLongestPalindromeLength(input);
//        System.out.println("Length of the longest palindrome: " + longestPalindromeLength);
        String str = "A man a plan a canal Panama";
        String newStr = str.replace(" ", "").toLowerCase();//! xoa khoang trang,chuyen ve chu thuong
        //! luu so lan xuat hien tung ky tu
        int countChar[] = new int[26];
        for (char c : newStr.toCharArray()) {
            countChar[c - 'a']++;
        }
        //! xac dinh palindrome
        int palindrome = 0;
        boolean countFound = false;
        for (int count : countChar) { //!  xac dinh so ky tu co so lan xuat hien chan
            if (count % 2 == 0) {
                palindrome += count;
            } else {
                palindrome += count - 1;
                countFound = true;
            }
        }
        if (countFound) {
            palindrome += 1;
        }
        System.out.println(palindrome);
    }

    public static int findLongestPalindromeLength1(String str) {
        int[] charCount = new int[128];
        int oddCount = 0;

        for (char c : str.toCharArray()) {
            charCount[c]++;
            if (charCount[c] % 2 != 0) {
                oddCount++;
            } else {
                oddCount--;
            }
        }

        return str.length() - Math.max(0, oddCount);
    }

    public static int findLongestPalindromeLength(String str) {
        str = str.trim();
        int[] charCount = new int[128];
        int oddCount = 0;

        for (char c : str.toCharArray()) {
            charCount[c]++;
        }

        for (int count : charCount) {
            if (count % 2 != 0) {
                oddCount++;
            }
        }

        if (oddCount <= 1) {
            return str.length();
        } else {
            return str.length() - oddCount + 1;
        }
    }
}
