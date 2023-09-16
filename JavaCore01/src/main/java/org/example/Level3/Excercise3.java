package org.example.Level3;

import java.util.Arrays;

public class Excercise3 {
    // Write a program that takes a list of integers as input
    // and returns the longest increasing subsequence of the numbers. ( Tìm độ dài của chuỗi con tịnh tiến dài nhất )
    // VD:
    // [3, 10, 2, 1, 20] 👉 3 ( The longest increasing subsequence is [3, 10, 20] )
    // [50, 3, 10, 7, 40, 80 ] 👉 4 ( the longest increasing subsequence is {3, 7, 40, 80} )
    public static int findLISLength(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n]; // Mảng dp để lưu độ dài của chuỗi tại mỗi vị trí
        dp[0] = 1; // Mỗi phần tử tạo thành một chuỗi có độ dài 1 ban đầu

        int maxLen = 1; // Độ dài của chuỗi con tăng dần dài nhất

        for (int i = 1; i < n; i++) {
            dp[i] = 1; // Mỗi phần tử tạo thành một chuỗi có độ dài 1
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }
    public static void main(String[] args) {
        int[] numbers = { 3, 10, 2, 1, 20 };
        int lisLength = findLISLength(numbers);
        System.out.println("Độ dài của chuỗi con tăng dần dài nhất là: " + lisLength);
    }

// [1 , 0 , 0 , 0 , 0 ]
// [1 , 2 , 0 , 0 , 0 ]
// [1 , 2 , 1 , 0 , 0 ]
// [1 , 2 , 1 , 1 , 0 ]
// [1 , 2 , 1 , 1 , 3 ]

}
