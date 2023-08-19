package org.example.Level4;

// Write a program that takes a list of integers as input and returns the length of the longest increasing subsequence
// of the numbers, with the additional constraint that no two adjacent elements in the subsequence can differ by more than 1.
// ( Khuyến khích dùng reduce )
// VD:
// Đầu vào: [1, 2, 3, 8, 6, 3] kết quả 3
// Đầu vào: [1, 2, 3, 8, 3, 2, 4, 5, 6, 7, 8, 9] kết quả 6
public class Excercise9 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 8, 3, 2, 4, 5, 6, 7, 8, 9, 10};
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
//            if (i == nums.length - 1) {
//                break;
//            }
            if (nums[i] < nums[i+1]) {
                count++;
            } else {
                count = 0;
            }
        }
        System.out.println(count);
    }

    // TODO: Sử dụng quy hoạch đông/chia để trị tối ưu hơn


}
