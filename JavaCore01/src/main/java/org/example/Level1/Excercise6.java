package org.example.Level1;

public class Excercise6 {
    // Write a program that takes a list of numbers as input and sorts the list in ascending order.
    public static void sortArrayNumsAndPrint(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] >= nums[j]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }

        }
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void main(String[] args) {
        int nums[] = {1, 2, 6, 9, 7, 5, 2 };
        sortArrayNumsAndPrint(nums);
    }
}
