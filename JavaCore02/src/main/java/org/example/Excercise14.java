package org.example;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//Bài tập 14:Hãy tạo một chương trình Java để nhập hai tập hợp (HashSet) các số nguyên từ người dùng.
// Hãy tìm và hiển thị các phần tử chung (giao) của hai tập hợp.
public class Excercise14 {
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
            for (int num1: nums1) {
                if (num == num1) {
                    rs.add(num);
                }
            }
        }
        System.out.println(rs);
    }
}
