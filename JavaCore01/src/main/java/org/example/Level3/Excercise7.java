package org.example.Level3;

import java.util.Scanner;

public class Excercise7 {
    // Write a program that takes a string as input and
    // returns the length of the longest palindrome that can be formed by rearranging the characters in the string.
    // Giải thích : palindrome	: Chuỗi ký tự mà đọc xuôi hay ngược đều giống nhau, vd: aba, uwu
    // VD: “A man a plan a canal Panama” 👉 21 (amanaplanacanalpanama)

    // ChatGPT
//    public static String findLongestPalindrome(String str) {
//        int n = str.length();
//        String longestPalindrome = "";
//
//        for (int i = 0; i < n; i++) {
//            // Tìm chuỗi Palindrome bắt đầu từ vị trí i, lớn nhất có thể
//            String palindrome = expandAroundCenter(str, i, i); // Trường hợp chuỗi có độ dài lẻ
//            String palindrome2 = expandAroundCenter(str, i, i + 1); // Trường hợp chuỗi có độ dài chẵn
//
//            // So sánh và lấy chuỗi Palindrome dài nhất
//            if (palindrome.length() > longestPalindrome.length()) {
//                longestPalindrome = palindrome;
//            }
//            if (palindrome2.length() > longestPalindrome.length()) {
//                longestPalindrome = palindrome2;
//            }
//        }
//
//        return longestPalindrome;
//    }
//
//    public static String expandAroundCenter(String str, int left, int right) {
//        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
//            left--;
//            right++;
//        }
//
//        // Khi vòng lặp kết thúc, chuỗi Palindrome là str.substring(left + 1, right)
//        return str.substring(left + 1, right);
//    }
//
//    public static void main(String[] args) {
//        String input = "babad";
//        String longestPalindrome = findLongestPalindrome(input);
//        System.out.println("Chuỗi Palindrome dài nhất trong chuỗi là: " + longestPalindrome);
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        int longestPalindromeLength = findLongestPalindromeLength(input);
        System.out.println("Length of the longest palindrome: " + longestPalindromeLength);
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
