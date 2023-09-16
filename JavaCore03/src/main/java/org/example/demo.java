package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class demo {
    // Cách này có thể xử lý được trường hợp có số âm
    private static final Map<String, Integer> precedence = new HashMap<>();

    static {
        precedence.put("+", 1);
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);
        precedence.put("u-", 3); // Unary minus - đánh dấu đây là số âm (thay cho dấu '-' và sẽ đứng đằng sau số đó)
    }

    public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<String> stack = new Stack<>();

        String[] tokens = infix.split(" ");

        for (String token : tokens) {
            if (isNumeric(token)) {
                postfix.append(token).append(" ");
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop(); // Pop '(' from the stack
            } else {
                while (!stack.isEmpty() && precedence.getOrDefault(token, 0) <= precedence.getOrDefault(stack.peek(), 0)) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }

        return postfix.toString();
    }

    public static double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();

        String[] tokens = postfix.trim().split(" ");

        for (String token : tokens) {
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                if (token.equals("u-")) {
                    double operand = stack.pop();
                    stack.push(-operand);
                } else {
                    double operand2 = stack.pop();
                    double operand1 = stack.pop();
                    double result = performOperation(token, operand1, operand2);
                    stack.push(result);
                }
            }
        }

        return stack.pop();
    }

    private static boolean isNumeric(String token) {
        return token.matches("-?\\d+(\\.\\d+)?");
    }

    private static double performOperation(String operator, double operand1, double operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public static void main(String[] args) {
        //String infixExpression = "-5*(2+3)-8/(-1+3)";
        String infixExpression = "(15+8-9*2)/(3-2*2)";
        //String infixExpression = "(6+9)*2-3";
        String[] infixTokens = tokenizeInfixExpression(infixExpression);
        StringBuilder modifiedInfix = new StringBuilder();

        for (int i = 0; i < infixTokens.length; i++) {
            String token = infixTokens[i];
            if (token.equals("-") && (i == 0 || infixTokens[i - 1].matches("[+\\-*/(]"))) {
                modifiedInfix.append("u- ");
            } else {
                modifiedInfix.append(token).append(" ");
            }
        }

        String postfixExpression = infixToPostfix(modifiedInfix.toString());
        String resultPostfix = postfixExpression.replace("u-", "-");
        System.out.println("Postfix expression: " + resultPostfix);

        double result = evaluatePostfix(postfixExpression);
        System.out.println("Result: " + result);
    }

    private static String[] tokenizeInfixExpression(String infixExpression) {
        infixExpression = infixExpression.replaceAll("\\s+", ""); // Loại bỏ dấu cách
        infixExpression = infixExpression.replaceAll("([()+\\-*/])", " $1 "); // Thêm khoảng trắng trước và sau các toán tử và ngoặc
        return infixExpression.trim().split("\\s+");
    }
}
