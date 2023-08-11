package org.example;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Bài tập 13: Hãy viết một chương trình Java để nhập một mảng các số nguyên từ người dùng
// và tạo một HashSet để lưu trữ các phần tử của mảng. Sau đó, hãy hiển thị các phần tử trùng lặp trong mảng.
public class Excercise13 {
    public static void main(String[] args) {
        int[] nums = {1,2,4,4,5,6,4,2,3,7,8,5,6,8};
//        List<Integer> rs = new ArrayList<>();
        Set<Integer> rs = new HashSet<>();
        Set<Integer> uniqueNums = new HashSet<>();

        for (int num:nums) {
            if (!uniqueNums.add(num)) {
                rs.add(num);
//                if (!rs.contains(num)) {
//                    rs.add(num);
//                }
            }
        }
        System.out.println(rs);
    }
}
