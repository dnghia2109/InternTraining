package org.example.Level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Excercise2 {
    // Write a program that takes a list of integers as input
    // and returns the maximum difference between any two elements in the list.
    // VD: [1, 2, 91, 9, 100] 👉 99 ( ket qua cua 100 - 1 )
    // hiệu max - min
    public static void main(String[] args) {
        int[] nums = {-1,-9,-2,-3,0};
        Arrays.sort(nums);
        System.out.println("Khoang cach lon nhat cua 2 phan tu trong mang: " + (nums[nums.length-1] - nums[0]));
    }

    public static int findMaximumDifferenceBetweenAny2Elements(int[] nums) {
        if (nums.length == 0) {
            throw new IllegalArgumentException("Mảng rỗng, mảng cần chứa ít nhất 2 phần tử.");
        }
        Arrays.sort(nums);
        return nums[nums.length-1] - nums[0];
    }
}
