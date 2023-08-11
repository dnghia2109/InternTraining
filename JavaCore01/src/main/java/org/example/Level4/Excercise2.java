package org.example.Level4;

public class Excercise2 {
    // Write a program that takes a list of integers as input and
    // returns the number of distinct subsequences of the list that sum up to a target value.
    //VD: 
    //Đầu vào [1, 2, 3, 4, 5] và 5 👉 2 ( vì 2 + 3 và 1 + 4 bằng 5 )
    //Đầu vào [1, 1, 2, 3, 4] và 5 👉 4 ( vì 4 tổ hợp có tổng bằng 5 )
    public static void main(String[] args) {
        int aimSum = 5;
        int[] nums = {1, 1, 2, 3, 4};
        countSumUp(nums, aimSum);
    }

    private static void countSumUp(int[] nums, int aimSum) {
        //int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length ; j++) {
                if (nums[i] + nums[j] == aimSum) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
