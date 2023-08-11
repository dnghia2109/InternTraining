package org.example.Level1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Excercise7 {
    // Write a program that takes a list of strings as input and sorts the list in alphabetical order.
    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>(List.of("Nghia", "Nam", "Son", "An"));
        Collections.sort(stringList); // mặc định asc
        System.out.println(stringList);

        // sắp xếp desc
        Collections.reverse(stringList);
        System.out.println(stringList);

    }
}
