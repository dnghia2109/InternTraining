package org.example;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// Bài tập 17: Hãy viết một chương trình Java để nhập một tập hợp (HashSet) các chuỗi từ người dùng
// và xóa tất cả các phần tử trùng lặp, chỉ giữ lại một phần tử duy nhất cho mỗi giá trị.
public class Excercise17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("- Nhập vào số lượng chuỗi: ");
        Set<String> hashSet = new HashSet<>();
        int x = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < x; i++) {
            System.out.println("- Nhập vào chuỗi thứ " + (i + 1) + ": ");
            hashSet.add(sc.nextLine());
        }

        System.out.println(hashSet);
    }
}
