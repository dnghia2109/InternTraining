package org.example.Level4;

import java.util.Arrays;
import java.util.Scanner;

// Write a program that takes a list of integers as input and returns
// the maximum product of any three distinct elements in the list.
// Ví dụ: [-10, -5, 0, 1, 2, 3, 4] 👉 200 (tích của -10, -5 và 4)
public class Excercise6 {
    public static void main(String[] args) {
        int[] arr = {4,6,3,1,0,8,9,10,11,5,6};
        Arrays.sort(arr);
        //Arrays.stream(arr).forEach(System.out::println);
        System.out.println(Math.max((arr[0] * arr[1] * arr[arr.length - 1]) , (arr[arr.length - 1] * arr[arr.length - 2] * arr[arr.length - 3])  ));
    }
//    -2 , 1, 2, ....,6, 7, 8, 9
//    -100 -2  1, ....,6, 7, 8, 9

//max1 max2 max3 vs max1 min1 min2
}

