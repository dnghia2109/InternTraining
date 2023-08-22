package org.example.Level4;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class Excercise2 {
    // Write a program that takes a list of integers as input and
    // returns the number of distinct subsequences of the list that sum up to a target value.
    //VD: 
    //Đầu vào [1, 2, 3, 4, 5] và 5 👉 2 ( vì 2 + 3 và 1 + 4 bằng 5 )
    //Đầu vào [1, 1, 2, 3, 4] và 5 👉 4 ( vì 4 tổ hợp có tổng bằng 5 )



//    public static int countDistinctSubsequences(List<Integer> nums, int target) {
//        int[] dp = new int[target + 1];
//        dp[0] = 1;
//
//        for (int num : nums) {
//            for (int j = target; j >= num; j--) {
//                dp[j] += dp[j - num];
//            }
//        }
//
//        return dp[target];
//    }


    //listNumber: [1, 1, 2, 3, 4]
    //target: 5
    //
    //    0   1   2   3   4   5
    //0   1   0   0   0   0   0
    //1   1   1   0   0   0   0
    //2   1   2   1   0   0   0
    //3   1   2   2   2   1   0
    //4   1   2   2   3   3   2
    //5   1   2   2   3   3   4

    // dp[i][j] bằng dp[i - 1][j] đại diện cho trường hợp không bao gồm phần tử thứ i trong dãy con có tổng bằng j.
    // Điều này xảy ra vì dp[i - 1][j] đã chứa toàn bộ các dãy con khác nhau có tổng bằng j mà không sử dụng phần tử thứ i.
    //
    // dp[i][j] += dp[i - 1][j - listNumber.get(i - 1)] đại diện cho trường hợp bao gồm phần tử thứ i trong dãy con có tổng bằng j.
    // Ở đây, listNumber.get(i - 1) đề cập đến giá trị của phần tử thứ i trong danh sách listNumber. j - listNumber.get(i - 1)
    // tương ứng với tổng cần tạo ra sau khi loại bỏ phần tử thứ i. Và dp[i - 1][j - listNumber.get(i - 1)] đại diện cho số lượng
    // dãy con khác nhau có tổng bằng j - listNumber.get(i - 1) sử dụng các phần tử từ phần tử đầu đến phần tử thứ i - 1.
    //
    // Như vậy, cả hai phần trong công thức dp[i][j] += dp[i - 1][j - listNumber.get(i - 1)] đều tham gia vào việc tính toán số lượng
    // các dãy con khác nhau có tổng bằng j và bao gồm phần tử thứ i hoặc không.

    public static void main(String[] args) {
        List<Integer> listNumber = new ArrayList<>(List.of(1, 1, 2, 3, 4));
        int target = 5;
        int n = listNumber.size();
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1; //! Trường hợp target = 0, tồn tại một dãy rỗng có tổng bằng 0
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j]; //! Không bao gồm phần tử thứ i
                if (listNumber.get(i - 1) <= j) {
                    dp[i][j] += dp[i - 1][j - listNumber.get(i - 1)]; //! Bao gồm phần tử thứ i
                }
            }
        }
        System.out.println(dp[n][target]);
    }
}
