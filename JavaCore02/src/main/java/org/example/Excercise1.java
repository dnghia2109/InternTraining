package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Excercise1 {
    // Bài tập 1: ArrayList Hãy tạo một chương trình Java để thực hiện các thao tác cơ bản trên một ArrayList. Hãy bắt đầu với một ArrayList chứa các số nguyên và thực hiện các thao tác sau:
    // Thêm các phần tử vào ArrayList.
    // Hiển thị các phần tử trong ArrayList.
    // Tính tổng của tất cả các phần tử trong ArrayList.
    // Tìm giá trị lớn nhất và nhỏ nhất trong ArrayList.
    // Xóa một phần tử cụ thể khỏi ArrayList.
    // Kiểm tra một phần tử có tồn tại trong ArrayList hay không.
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(1,2,3,4,3,5,6,7));

        list.add(8);

        // Hiển thị các phần tử trong ArrayList.
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println(list.size());

        // Tính tổng của tất cả các phần tử trong ArrayList.
        int sum = 0;
        for (int num: list) {
            sum+=num;
        }
        System.out.println(sum);

        list = list.stream().sorted((o1, o2)->o1.compareTo(o2)).collect(Collectors.toList());
        System.out.println(list);


    }



}
