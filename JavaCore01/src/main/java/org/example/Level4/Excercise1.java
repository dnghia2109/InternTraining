package org.example.Level4;

public class Excercise1 {
    // Write a program that takes a list of integers as input and returns the minimum number of moves required to sort the list in ascending order using bubble sort.
    //		Đầu vào 1 dãy số bất kỳ
    //Đầu ra là số lần lặp để hoàn thành việc sắp xếp nổi bọt
    //Gợi ý: Tìm hiểu về sắp xếp nổi bọt ( bubble sort ). Tìm số lần sắp xếp nổi bọt cần để hoàn thành việc sắp xếp với mảng đầu vào
    //VD: [3, 1, 4, 2, 6, 5] cần 4 lần sắp xếp để hoàn thành
    public static void main(String[] args) {
        int[] nums = {1,5,7,3,2,9,8};
        bubbleSort(nums);
    }

    private static void bubbleSort(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    count++;
                }
            }
        }

        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println("\n" + count);
    }

}
