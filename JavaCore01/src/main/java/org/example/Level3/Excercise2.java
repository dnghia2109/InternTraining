package org.example.Level3;

import java.util.Arrays;
import java.util.Collections;

public class Excercise2 {
    // Write a program that takes a list of integers as input
    // and returns the maximum difference between any two elements in the list.
    // VD: [1, 2, 91, 9, 100] 👉 99 ( ket qua cua 100 - 1 )
    // hiệu max - min
    public static void main(String[] args) {
        int nums[] = {1, 2, 91, 9, 100, -5};
        Arrays.sort(nums);
        System.out.println("Khoang cach lon nhat cua 2 phan tu trong mang: " + (nums[nums.length-1] - nums[0]));
    }
}
