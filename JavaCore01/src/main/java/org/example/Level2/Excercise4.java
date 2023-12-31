package org.example.Level2;

public class Excercise4 {
    // Write a program that takes a list of numbers as input
    // and returns the sum of the numbers that are divisible by both 3 and 5.
    public static void main(String[] args) {
        int nums[] = {1,5,6,7,15,5,3,25,135,60,90};
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] % 3 == 0) && (nums[i] % 5== 0)) {
                sum += nums[i];
            }
        }
        System.out.println(sum);
    }

    public static int sumNumDivisibleBoth3And5(double[] nums) {
        int sum = 0;
        for (double num : nums) {
            if ((num % 3 == 0) && (num % 5== 0)) {
                sum += num;
            }
        }
        return sum;
    }

    // Có thể dùng stream
    // int[] newArr = Arrays.stream(nums)
    //        .filter(num -> num % 3 == 0 && num % 5 == 0)
    //        .toArray();
    //
    //
    // for (int num : newArr) {
    //        sum += num;
    //    }
}
