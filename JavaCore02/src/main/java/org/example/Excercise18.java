package org.example;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// Bài tập 18: Hãy viết một chương trình Java để nhập một tập hợp (HashSet)
// các chuỗi từ người dùng và đếm số lượng phần tử trong tập hợp.
public class Excercise18 {
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
        System.out.println("- Số lượng phần tử trong tập hợp: " + hashSet.size());
    }
}
