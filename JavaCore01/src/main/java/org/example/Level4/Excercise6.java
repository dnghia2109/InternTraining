package org.example.Level4;

import java.util.Arrays;
import java.util.Scanner;

// Write a program that takes a list of integers as input and returns
// the maximum product of any three distinct elements in the list.
// Ví dụ: [-10, -5, 0, 1, 2, 3, 4] 👉 200 (tích của -10, -5 và 4)
public class Excercise6 {
    public static void main(String[] args) {
        int[] arr1 = {4,6,3,1,0,8,9,10,11,5,6};
        int x = arr1.length;
        Arrays.sort(arr1);
        int max3 = arr1[x - 1] * arr1[x - 2] * arr1[x - 3];
        int max4 = arr1[0] * arr1[1] * arr1[x - 1];
        int rs = Math.max(max3, max4);
        System.out.println("tich lon nhat: "+ rs);
    }
}

