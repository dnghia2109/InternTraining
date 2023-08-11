package org.example;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//Bài tập 15: Hãy tạo một chương trình Java để nhập hai tập hợp (HashSet) các số nguyên từ người dùng.
// Hãy tìm và hiển thị tất cả các phần tử thuộc cả hai tập hợp (hợp) của hai tập hợp này.
public class Excercise15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Integer> nums = new HashSet<>();
        System.out.println("- Nhập số lượng phần tử trong Set1: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            nums.add(sc.nextInt());
        }
        System.out.println("--> Set1:" + nums);

        Set<Integer> nums1 = new HashSet<>();
        System.out.println("- Nhập số lượng phần tử trong Set2: ");
        int n1 = sc.nextInt();
        for (int i = 0; i < n1; i++) {
            nums1.add(sc.nextInt());
        }
        System.out.println("--> Set2:" + nums1);

        Set<Integer> rs = new HashSet<>();
        for (int num: nums) {
            rs.add(num);
        }
        for (int num1: nums1) {
            rs.add(num1);
        }
        System.out.println(rs);
    }
}
