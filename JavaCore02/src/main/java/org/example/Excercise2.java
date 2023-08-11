package org.example;

import java.util.HashSet;
import java.util.Set;

public class Excercise2 {
    // Bài tập 2: HashSet Hãy tạo một chương trình Java để thực hiện các thao tác cơ bản trên một HashSet.
    // Hãy tạo một HashSet chứa tên của các quốc gia và thực hiện các thao tác sau:
    // Thêm các tên quốc gia vào HashSet.
    // Hiển thị danh sách các quốc gia đã thêm vào.
    // Kiểm tra xem một quốc gia có tồn tại trong HashSet hay không.
    // Xóa một quốc gia cụ thể khỏi HashSet.
    // Tính số lượng các quốc gia có trong HashSet.

    public static void main(String[] args) {
        Set<String> nations = new HashSet<>();
        nations.addAll(Set.of("Vietnam", "Japan", "USA", "France", "China"));
        // nations.add("China");
        for (String nation:nations) {
            System.out.println(nation);
        }

        System.out.println(nations.contains("China"));
        System.out.println(nations.contains("Chinaa"));

        nations.remove("China");

        nations.size();
    }
}
