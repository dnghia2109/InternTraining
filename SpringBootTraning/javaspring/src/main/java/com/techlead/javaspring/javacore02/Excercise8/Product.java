package com.techlead.javaspring.javacore02.Excercise8;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {
    private int id;
    private String name;
    private int price;
    private int quantity;

    private static int countId = 1;

    public Product() {
        this.id = countId++;
    }

    public Product(String name, int price, int quantity) {
        this.id = countId++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
