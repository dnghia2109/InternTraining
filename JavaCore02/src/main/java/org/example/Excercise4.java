package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Excercise4 {
    // Bài tập 4: Sắp xếp ArrayList Hãy tạo một chương trình Java
    // để sắp xếp một ArrayList chứa các số nguyên theo thứ tự tăng dần và giảm dần.
    // Sử dụng các phương thức sắp xếp có sẵn trong Collection Framework để làm điều này.
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(1,7,9,3,8,5,4,3,2));
        Collections.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(nums);

        Collections.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }
}
