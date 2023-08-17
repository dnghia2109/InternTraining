package com.techlead.javaspring.javacore02.Excercise12;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.ParseException;
import java.util.Date;

@AllArgsConstructor
@Data
public class Product {
    private int id;
    private String name;
    private int price;
    private Date date;



    private static int countId = 1;

    public Product() throws ParseException {
        this.id = countId++;
    }

    public Product(String name, int price, Date date) throws ParseException {
        this.id = countId++;
        this.name = name;
        this.price = price;
        this.date = date;
    }
}
