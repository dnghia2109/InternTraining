package org.example;

import java.util.HashMap;
import java.util.Scanner;

// Tạo từ điển đơn giản Hãy tạo một từ điển đơn giản sử dụng HashMap. Cho phép người dùng thêm từ và định nghĩa của từ
// vào từ điển. Sau đó, cho phép người dùng tra cứu từ điển bằng cách nhập từ cần tìm kiếm và hiển thị định nghĩa của từ đó.
public class Excercise11 {
    public static HashMap<String, String> dictionary = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){

        boolean flag = true;
        int choice;
        do{
            System.out.println("1. Add word ");
            System.out.println("2. Find word");
            System.out.println("3. Exit");
            System.out.println("Select: ");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.println("- Add word");
                    addTuVung();
                    break;
                case 2:
                    System.out.println("- Find");
                    findTuVung();
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    System.out.println("Please select from 1-3.");
            }

        }while (flag);

        scanner.close();
    }

    public static void addTuVung(){
        System.out.println("- Type word: ");
        scanner.nextLine();
        String key = scanner.nextLine();
        System.out.println("- Meaning: ");
        String value = scanner.nextLine();

        if(dictionary.containsKey(key)){
            System.out.println("This word already exists");
        } else {
            dictionary.put(key, value);
            System.out.println("Add successful");
        }
    }
    public static void findTuVung(){
        System.out.println("- Type word: ");
        scanner.nextLine();
        String key = scanner.nextLine();

        if(dictionary.containsKey(key)){
            System.out.println(key + " : " + dictionary.get(key));
        } else {
            System.out.println("Not found " + '"' + key + '"');
        }
    }
}
