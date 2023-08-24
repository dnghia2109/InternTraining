package org.example.Level3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Excercise5 {
    // Write a program that takes a list of numbers as input and returns
    // the smallest positive integer that cannot be represented as the sum of any subset of the list.
    // Ví dụ: [1, 2, 3, 7, 8, 20] => 42
    // Giải thích
    // Chạy vòng lặp từ 1 đến số dương vô cùng:
    // Số 1 có trong mảng => loại
    //… Tương tự với 2 và 3
    // Số 4 không có trong mảng nó là tổng của 1 và 3 👉 loại 4
    //… Tương tự đến 42 thì không có tập hợp số nào trong mảng là 42=> 42 là kết quả
    public static void main(String[] args) {
        List<Integer> arrNum = new ArrayList<>(List.of(1, 2, 3, 7, 8, 20));

        Collections.sort(arrNum);
        int smallest = 1;
        for (int number : arrNum) {
            if (number <= smallest) {
                smallest += number;
            }
        }
        System.out.println(smallest);

    }
}
