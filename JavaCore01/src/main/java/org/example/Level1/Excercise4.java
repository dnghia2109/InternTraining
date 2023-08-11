package org.example.Level1;

import java.util.Scanner;

public class Excercise4 {
    // Write a program that takes a list of numbers as input and displays the largest number in the list
    public static void main(String[] args) {
        int nums [] = {1, 2, 4, 7, 9, 3, 4, 2};
        int maxNum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxNum) {
                maxNum = nums[i];
            }
        }
        System.out.println(maxNum);
    }
}
