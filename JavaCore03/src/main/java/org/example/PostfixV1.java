package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class PostfixV1 {

    // Check infix nhập vào null
    public  boolean isInputNull(String string) {
        return string.isEmpty();
    }

    private  boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // Check đóng mở ngoặc nếu thiếu return exception
    public  boolean checkBrackets(String string) {
        string = string.replaceAll("\\s+", "");
        int check_value = 0;
        for (char c:string.toCharArray()) {
            if (Character.isDigit(c) || isOperator(c) || Character.isLetter(c)) {
                continue;
            }
            if (c == '(') {
                check_value++;
            } else {
                check_value--;
                if (check_value < 0) {
                    return false;
                }
            }
        }
        if (check_value > 0) {
            return false;
        }
        return true;
    }

    // Check các trường hợp có nhiều toán tử được nhập liên tiếp nhau: +- ; +* ; +-/
    private boolean checkOperatorContiguousOperator(String trungTo) {
        //boolean check = true;
        int count = 0;
        char[] arr = trungTo.toCharArray();
        for (int i = 0; i < arr.length - 1; i++) {
            if (isOperator(arr[i]) && isOperator(arr[i + 1])) {
                //System.out.println("-- Bạn đang nhập sai định dạng, đang có 2 toán tử liền kề nhau!!!!");
                return false;
            }
        }
        return true;
    }

// ================================================================================================================================
    // Ver1

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
        trungTo.trim().replaceAll("\\s+", "");

        if (isInputNull(trungTo)) {
            throw new IllegalArgumentException("Biểu thức nhập vào rỗng");
        }

        if (!checkBrackets(trungTo)) {
            throw new IllegalArgumentException("Biểu thức nhập vào thiếu ngoặc vui lòng điền đẩy đủ đóng mở ngoặc");
        }

        if (!checkOperatorContiguousOperator(trungTo)) {
            throw new IllegalArgumentException("Bạn đang nhập sai định dạng, đang có 2 toán tử liền kề nhau!!!!");
        }

        StringBuilder hauTo = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char c : trungTo.toCharArray()) {
         // Nếu là toán hạng (số), thêm vào biểu thức hậu tố
            if (Character.isDigit(c) || Character.isLetter(c)) {
                hauTo.append(c);
            } else if (isOperator(c)) {
                 while (!stack.isEmpty() && (uuTien(stack.peek()) >= uuTien(c))) {
                     hauTo.append(stack.pop());  // Đẩy toán tử từ ngăn xếp vào biểu thức hậu tố nếu có mức ưu tiên cao hơn hoặc bằng
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
//            hauTo.append(' '); // Thêm một khoảng trắng sau mỗi phần tử (bao gồm cả số và toán tử)
        }
        while (!stack.empty()) {
            hauTo.append(stack.pop());  // Sau khi duyệt hết biểu thức, đẩy các toán tử còn lại từ stack vào biểu thức hậu tố
        }

        return hauTo.toString().trim();  // Trả về biểu thức hậu tố tương ứng
    }

    //158+92*-322*-/
    public double evaluatePostfixV1(String postfix) {
        Stack<Double> stack = new Stack<>();
        for (char c : postfix.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push((double) (c - '0'));
            } else if (isOperator(c)) {
                double o1 = stack.pop();
                double o2 = stack.pop();
                double rs;
                switch (c) {
                    case '+':
                        rs = o1 + o2;
                        break;
                    case '-':
                        rs = o2 - o1;
                        break;
                    case '*':
                        rs = o1 * o2;
                        break;
                    case '/':
                        rs = o2 / o1;
                        break;
                    default:
                        throw new IllegalArgumentException("Đầu vào không hợp lệ");
                }
                stack.push(rs);
            }
        }
        return stack.pop();
    }

// ================================================================================================================================
    //Ver2

    //15 8 + 9 2 * - 3 2 2 * - /
    public String infixToPostfixV2(String infix) {
        infix = infix.replaceAll("\\s+", ""); // Loại bỏ tất cả các khoảng trắng

        if (isInputNull(infix)) {
            throw new IllegalArgumentException("Biểu thức nhập vào rỗng");
        }

        if (!checkBrackets(infix)) {
            throw new IllegalArgumentException("Biểu thức nhập vào thiếu ngoặc vui lòng điền đầy đủ đóng mở ngoặc");
        }

        if (!checkOperatorContiguousOperator(infix)) {
            throw new IllegalArgumentException("Bạn đang nhập sai định dạng, đang có 2 toán tử liền kề nhau!!!!");
        }

        StringBuilder postfix = new StringBuilder();
        StringBuilder number = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : infix.toCharArray()) {
            // Infix chỉ có các số
            if (Character.isDigit(c) ) { // number  = 15
                number.append(c);
            } else {
                if (number.length() > 0) {
                    postfix.append(number).append(" ");
                    number.setLength(0);
                }
                if (c == '(') {
                    stack.push(c);
                }
                else if (c == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        postfix.append(stack.pop()).append(" ");
                    }
                    stack.pop();
                } else {
                    while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                        postfix.append(stack.pop()).append(" ");
                    }
                    stack.push(c);
                }
            }
        }

        // Thêm số cuối cùng trogn infix vào postfix à thêm " " vào sau
        if (number.length() > 0) {
            postfix.append(number).append(" ");
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(' ');
        }

        return postfix.toString().trim();
    }

    public int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return 0;
    }


    public double evaluatePostfixV2(String postfixExpression) {
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < postfixExpression.length(); i++) {
            char c = postfixExpression.charAt(i);
            if (Character.isDigit(c)) {
                StringBuilder number = new StringBuilder();
                while (i < postfixExpression.length() && (Character.isDigit(c))) {
                    number.append(c);
                    i++;
                    if (i < postfixExpression.length()) {
                        c = postfixExpression.charAt(i);
                    }
                }
                stack.push(Double.parseDouble(number.toString()));
            } else if (c != ' ') {
                double o1 = stack.pop();
                double o2 = stack.pop();
                double result = performOperation(c, o1, o2);
                stack.push(result);
            }
        }

        return (double) Math.round(stack.pop() * 1000) / 1000;
    }

    public double performOperation(char operator, double operand1, double operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand2 - operand1;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand1 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand2 / operand1;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

     public static void main(String[] args) {
        PostfixV1 postfixV1 = new PostfixV1();
         System.out.println("Nhập vào biểu thức: ");
         String string = new Scanner(System.in).nextLine().trim().replaceAll("\\s+", "");
         String hauTo2 = postfixV1.infixToPostfixV2(string);
         System.out.println("=> Chuyển sang hậu tố: " + hauTo2);
         System.out.println("=> Giá trị biểu thức: " + postfixV1.evaluatePostfixV2(hauTo2));

         String hauTo = postfixV1.chuyenTrungToSangHauTo(string);
         System.out.println("=> Chuyển sang hậu tố: " + hauTo);
         System.out.println("=> Giá trị biểu thức: " + postfixV1.evaluatePostfixV1(hauTo));
//         System.out.println(checkBrackets(string) ? "Dãy nhập vào hợp lệ ngoặc" : "Dãy nhập vào KHÔNG hợp lệ ngoặc ");
     }
}





