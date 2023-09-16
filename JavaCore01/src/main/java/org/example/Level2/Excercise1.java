package org.example.Level2;

public class Excercise1 {
    // Write a program that takes a list of numbers as input and returns the second largest number in the list.
    //		VD: [70, 11, 20, 4, 100] 👉 70
    public static void main(String[] args) {
        int[] arrNumber = {1,2,6,7,9,8};
        System.out.println("- Số lớn thứ 2: " + findSecondLargest(arrNumber));
    }

    public static int findSecondLargest(int[] arrNumber) {
        if (arrNumber.length < 2) {
            throw new IllegalArgumentException("Array must have at least 2 elements !!!");
        }

        int max = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int i = 0; i < arrNumber.length ; i++) {
            if (max < arrNumber[i]){
                max2 = max;
                max = arrNumber[i];
            } else if (max2 < arrNumber[i] && arrNumber[i] < max) {
                max2 = arrNumber[i];
            }
        }
        return max2;
    }

    // public static void
}
