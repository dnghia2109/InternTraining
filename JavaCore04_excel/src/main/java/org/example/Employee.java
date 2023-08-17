package org.example;

import lombok.Data;

import java.util.List;

@Data
public class Employee {
    private int id;
    private String name;
    List<NgayCong> listNgayCong;


//    private int n;
}
