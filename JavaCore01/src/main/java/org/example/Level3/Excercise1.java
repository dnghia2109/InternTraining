package org.example.Level3;

public class Excercise1 {
    // Write a program that takes a list of numbers as input and returns the second smallest number in the list.
    public static void main(String[] args) {
        int arrNumber[] = {1,2,3,-4,5,6,7,9,8};
        int min = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int j : arrNumber) {
            if (min > j) {
                min2 = min;
                min = j;
            } else if (min2 > j && j > min) {
                min2 = j;
            }
        }
        System.out.println(min);
        System.out.println(min2);
    }
}
