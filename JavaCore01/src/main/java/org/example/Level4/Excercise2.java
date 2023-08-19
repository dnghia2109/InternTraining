package org.example.Level4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Excercise2 {
    // Write a program that takes a list of integers as input and
    // returns the number of distinct subsequences of the list that sum up to a target value.
    //VD: 
    //Đầu vào [1, 2, 3, 4, 5] và 5 👉 2 ( vì 2 + 3 và 1 + 4 bằng 5 )
    //Đầu vào [1, 1, 2, 3, 4] và 5 👉 4 ( vì 4 tổ hợp có tổng bằng 5 )
//    public static void main(String[] args) {
//        int aimSum = 5;
//        int[] nums = {1, 1, 2, 3, 4};
//        countSumUp(nums, aimSum);
//    }
//
//    private static void countSumUp(int[] nums, int aimSum) {
//        //int sum = 0;
//        int count = 0;
//        for (int i = 0; i < nums.length - 1; i++) {
//            for (int j = i + 1; j < nums.length ; j++) {
//                if (nums[i] + nums[j] == aimSum) {
//                    count++;
//                }
//            }
//        }
//        System.out.println(count);
//    }


//    public static int countDistinctSubsequences(List<Integer> nums, int target) {
//        int[] dp = new int[target + 1];
//        dp[0] = 1;
//
//        for (int num : nums) {
//            for (int j = target; j >= num; j--) {
//                dp[j] += dp[j - num];
//            }
//        }
//
//        return dp[target];
//    }

    public static void main(String[] args) {
        List<Integer> listNumber = new ArrayList<>(List.of(1, 1, 2, 3, 4));
        int target = 5;
        int n = listNumber.size();
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1; //! Trường hợp target = 0, tồn tại một dãy rỗng có tổng bằng 0
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j]; //! Không bao gồm phần tử thứ i
                if (listNumber.get(i - 1) <= j) {
                    dp[i][j] += dp[i - 1][j - listNumber.get(i - 1)]; //! Bao gồm phần tử thứ i
                }
            }
        }
        System.out.println( dp[n][target]);
    }
}
