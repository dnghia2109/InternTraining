package com.techlead.javaspring.javacore03;

import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class Java03Service {
    public boolean kiemTraToanTu(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public int uuTien(char c) {
        if (c == '(') {
            return 0;
        }
        if (c == '+' || c == '-') {
            return 1;
        }
        if (c == '*' || c == '/') {
            return 2;
        }
        return 3;
    }

    public String chuyenTrungToSangHauTo(String trungTo) {
        StringBuilder hauTo = new StringBuilder();  // StringBuilder để lưu biểu thức hậu tố (nên sd StringBuilder hown String )
        Stack<Character> stack = new Stack<>();  // stack để lưu các toán tử tạm thời

        for (char c : trungTo.toCharArray()) {
            // Check là số thêm vào biểu thức hậu tố
            if (Character.isDigit(c)) {
                hauTo.append(c);
            } else if (kiemTraToanTu(c)) {
                while (!stack.isEmpty() && (uuTien(stack.peek()) >= uuTien(c))) {
                    hauTo.append(stack.pop());  // Đẩy toán tử từ ngăn xếp vào hauto nếu có mức ưu tiên cao hơn hoặc bằng
                }
                stack.push(c);  // Đẩy toán tử hiện tại vào ngăn xếp
            } else if (c == '(') {
                stack.push(c);  // Nếu là dấu mở ngoặc, đẩy vào ngăn xếp
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    // Khi gặp dấu đóng ngoặc, đẩy các toán tử từ ngăn xếp vào biểu thức hậu tố cho đến khi gặp dấu mở ngoặc
                    hauTo.append(stack.pop());
                }
                stack.pop();  // Xóa dấu mở ngoặc khỏi ngăn xếp
            }
        }

        while (!stack.empty()) {
            hauTo.append(stack.pop());  // Sau khi duyệt hết biểu thức, đẩy các toán tử còn lại từ ngăn xếp vào biểu thức hậu tố
        }

        return hauTo.toString();
    }

    public double tinhGiaTri(String hauTo) {
        Stack<Double> stack = new Stack<>();
        for (char c : hauTo.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push((double) (c - '0'));
            } else if (kiemTraToanTu(c)) {
                double o1 = stack.pop();
                double o2 = stack.pop();
                double ketQua;
                switch (c) {
                    case '+':
                        ketQua = o1 + o2;
                        break;
                    case '-':
                        ketQua = o2 - o1;
                        break;
                    case '*':
                        ketQua = o1 * o2;
                        break;
                    case '/':
                        ketQua = o2 / o1;
                        break;
                    default:
                        throw new IllegalArgumentException("dau vao ko hop le");
                }
                stack.push(ketQua);
            }
        }
        return stack.pop();
    }
}
