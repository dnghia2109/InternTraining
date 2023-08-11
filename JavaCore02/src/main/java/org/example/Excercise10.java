package org.example;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Bài tập 10: Kiểm tra phân phối điểm số Hãy viết một chương trình Java để kiểm tra phân phối điểm số của một lớp học.
// Người dùng sẽ nhập danh sách điểm số của sinh viên và
// chương trình sẽ tính số lượng sinh viên đạt điểm cao (>= 8.0),
// số lượng sinh viên đạt điểm trung bình (>= 5.0 và < 8.0) và số lượng sinh viên trượt (dưới 5.0).
// Sử dụng HashMap để lưu trữ điểm số làm key và số lượng sinh viên đạt điểm tương ứng làm value.
public class Excercise10 {
//    public static HashMap<>
    public static void main(String[] args) {
        Map<Double, Integer> scoreMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("- Nhập số lượng sinh viên: ");
        int numStudents = scanner.nextInt();

        for (int i = 0; i < numStudents; i++) {
            System.out.print("- Nhập điểm số của sinh viên thứ " + (i + 1) + ": ");
            double score = scanner.nextDouble();
            if (scoreMap.containsKey(score)) {
                int count = scoreMap.get(score);
                scoreMap.put(score, count + 1);
            } else {
                scoreMap.put(score, 1);
            }
        }


        System.out.println("- Số lượng sinh viên đạt điểm cao: " + getScoreCountInRange(scoreMap, 8.0, 10d));
        System.out.println("- Số lượng sinh viên đạt điểm trung bình: " + getScoreCountInRange(scoreMap, 5.0, 8.0));
        System.out.println("- Số lượng sinh viên trượt: " + getScoreCountInRange(scoreMap, 0d, 5.0));

        scanner.close();
    }

    public static int getScoreCountInRange(Map<Double, Integer> scoreMap, double minScore, double maxScore) {
        int count = 0;
        for (Map.Entry<Double, Integer> entry : scoreMap.entrySet()) {
            double score = entry.getKey();
            if (score >= minScore && score < maxScore) {
                count += entry.getValue();
            }
        }
        return count;
    }
}
