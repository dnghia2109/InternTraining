package com.techlead.javaspring.javacore02.Excercise8;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductRequest {
    private String name;
    private int price;
    private int quantity;
}
