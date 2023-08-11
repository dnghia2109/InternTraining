package org.example;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//Bài tập 16: Hãy viết một chương trình Java để nhập một tập hợp (HashSet) các số nguyên từ người dùng
// và tìm phần tử lớn nhất và nhỏ nhất trong tập hợp.
public class Excercise16 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Integer> nums = new HashSet<>();
        System.out.println("- Nhập số lượng phần tử trong Set1: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            nums.add(sc.nextInt());
        }
        System.out.println("--> Set1:" + nums);

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num:nums) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        System.out.printf("Max: %d, Min: %d",max,min);
    }
}
