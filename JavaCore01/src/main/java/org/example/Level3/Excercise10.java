package org.example.Level3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Excercise10 {
    // Write a program that takes a list of strings as input
    // and returns the list sorted by the number of distinct characters in each string, with the shortest strings appearing first.
    //VD: ['apple', 'banana', 'orange', 'kiwi', 'strawberry'] 👉 ['kiwi', 'apple', 'orange', 'banana', 'strawberry']

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>(List.of("apple", "banana", "orange", "kiwi", "strawberry"));
        Collections.sort(stringList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        System.out.println(stringList);
    }

}
