package org.example;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Bài tập 7: Tính toán điểm trung bình của sinh viên
// Hãy viết một chương trình Java để tính điểm trung bình của các sinh viên trong lớp học.
// Sử dụng HashMap để lưu trữ tên của sinh viên làm key và danh sách điểm số làm value.
// Sau đó, tính điểm trung bình cho mỗi sinh viên và hiển thị kết quả.
public class Excercise7 {
    public static void main(String[] args) {
        Map<String, List<Double>> studentListPoint = new HashMap<>();
        studentListPoint.put("Nam", new ArrayList<>(List.of(8.75, 6.75, 8d)));
        studentListPoint.put("Nam", new ArrayList<>(List.of(8.75, 7d, 6.35)));
        studentListPoint.put("Vinh", new ArrayList<>(List.of(8.75, 7d, 9.35)));
        studentListPoint.put("John", new ArrayList<>(List.of(8.05, 5d, 8.35)));
        studentListPoint.put("Paul", new ArrayList<>(List.of(5.5, 5d, 6.35)));
        studentListPoint.put("Nghia", new ArrayList<>(List.of(8.5, 7d, 8.5)));
        studentListPoint.put("Huong", new ArrayList<>(List.of(9d, 8.5, 9.555)));
        studentListPoint.put("Hung", new ArrayList<>(List.of(5.5, 5d, 7.565)));
        System.out.println(studentListPoint);
        for(Map.Entry<String,List<Double>> stu: studentListPoint.entrySet()){
            String stuName = stu.getKey();
            List<Double> listPoints = stu.getValue();
            double pointAvg = diemTB(listPoints);
            System.out.println(stuName + " - "+ pointAvg);
        }
    }

    public static double diemTB(List<Double> listPoints) {
        int sum = 0;
        for (double point: listPoints) {
            sum += point;
        }
        return sum / listPoints.size();
    }
}
