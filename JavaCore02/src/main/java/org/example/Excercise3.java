package org.example;

import java.util.HashMap;

// HashMap Hãy tạo một chương trình Java để thực hiện các thao tác cơ bản trên một HashMap.
// Hãy tạo một HashMap để lưu trữ cặp key-value với key là tên của một người và value là tuổi của người đó. Thực hiện các thao tác sau:
// Thêm các cặp key-value vào HashMap.
// Hiển thị danh sách tên của tất cả các người trong HashMap.
// Tìm tuổi của một người dựa trên tên của họ.
// Xóa một người cụ thể khỏi HashMap.
// Kiểm tra xem một người có tồn tại trong HashMap hay không.
public class Excercise3 {
    public static void main(String[] args) {
        HashMap<String, Integer> hashMap1 = new HashMap<>();

        hashMap1.put("Nghia",22);
        hashMap1.put("Nghia2",23);
        hashMap1.put("Cường",24);
        hashMap1.put("Trường",25);
        hashMap1.put("Trinh",26);
        // Hiển thị danh sách tên của tất cả các người trong HashMap (key cua hashmap)
        for (String key: hashMap1.keySet()){
            System.out.println(key + " - " + hashMap1.get(key));
        }
        System.out.println();
        // T tuổi của một người dựa trên tên của họ
        System.out.println("Tuổi của Nghia: "+ hashMap1.get("Nghia"));

        // Xóa một người cụ thể khỏi HashMap.
        hashMap1.remove("Trinh");
        System.out.println("- DS sau khi xóa: ");
        for (String key: hashMap1.keySet()){
            System.out.print(key+" ");
        }
        System.out.println();
        // Kiểm tra xem một người có tồn tại trong HashMap hay không.
        System.out.println(hashMap1.containsKey("Nghia"));
    }
}
