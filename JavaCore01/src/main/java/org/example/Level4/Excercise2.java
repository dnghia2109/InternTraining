package org.example.Level4;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class Excercise2 {
    // Write a program that takes a list of integers as input and
    // returns the number of distinct subsequences of the list that sum up to a target value.
    //VD:
    //Đầu vào [1, 1, 2, 3, 4] và 5 👉 4 ( vì 4 tổ hợp có tổng bằng 5 )

    //listNumber: [1, 1, 2, 3, 4]
    //target: 5
    //
    //    0   1   2   3   4   5
    //0   1   0   0   0   0   0
    //1   1   1   0   0   0   0
    //2   1   2   1   0   0   0
    //3   1   2   2   2   1   0
    //4   1   2   2   3   3   2
    //5   1   2   2   3   3   4



    public static void main(String[] args) {
//        List<Integer> listNumber = new ArrayList<>(List.of(1, 1, 2, 3, 4));
//        int target = 5;
//        int n = listNumber.size();
//        int[][] dp = new int[n + 1][target + 1];
//        for (int i = 0; i <= n; i++) {
//            dp[i][0] = 1; //! Trường hợp target = 0, tồn tại một dãy rỗng có tổng bằng 0
//        }
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= target; j++) {
//                dp[i][j] = dp[i - 1][j]; //! Không bao gồm phần tử thứ i
//                if (listNumber.get(i - 1) <= j) {
//                    dp[i][j] += dp[i - 1][j - listNumber.get(i - 1)]; //! Bao gồm phần tử thứ i
//                }
//            }
//        }
//        System.out.println(dp[n][target]);


    }

    public static int countDistinctSubsequences(int[] nums, int target){
        int[] dp = new int[target + 1];
        dp[0] =1;

        for(int i=0; i< nums.length; i++){
            for(int j = target; j>= nums[i]; j--){
                if(nums[i] != target) {
                    dp[j] += dp[j - nums[i]];
                }
            }
        }
        return dp[target];
    }

    public static int countDistinctSubsequencesSumUpToTargetValue(int target, List<Integer> listNumber) {

        return 0;
    }
}
