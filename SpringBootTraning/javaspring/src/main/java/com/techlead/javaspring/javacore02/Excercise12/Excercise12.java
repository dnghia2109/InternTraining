package com.techlead.javaspring.javacore02.Excercise12;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


// Bài tập 12: Chúng ta có 1 list/set các các sản phẩm bao gồm các thông tin tên, mã, giá bán, ngày sản xuất. Hãy viết 1 đoạn chương trình để sắp xếp các sản phẩm theo các tiêu chí sau:
// Theo tên
// Theo giá bán
// Theo ngày sản xuất
// Theo giá bán và ngày sản xuất
@Component
public class Excercise12 {
    public static List<Product> productList = new ArrayList<>();

    public Excercise12() throws ParseException {
        initData();
    }

    public List<Product> getAllProduct() {
        return productList;
    }

    public List<Product> sortByPriceAndDate() {
        productList.sort(Comparator
                .comparingInt(Product::getPrice)
                .thenComparing(Product::getDate)
        );
        return productList;
    }

    public List<Product> sortByDate() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return productList;
    }

    public List<Product> sortByName() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return productList;
    }

    public List<Product> sortByPrice() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o2.getPrice() - o1.getPrice();
            }
        });
        return productList;
    }

    public static void initData() throws ParseException {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        productList.addAll(List.of(
                new Product("sp1", 20000, simpleDateFormat.parse("21-09-2022")),
                new Product("sp2", 30000, simpleDateFormat.parse("21-08-2021")),
                new Product("sp3", 40000, simpleDateFormat.parse("01-10-2022")),
                new Product("sp4", 780000, simpleDateFormat.parse("21-10-2022")),
                new Product("sp5", 60000, simpleDateFormat.parse("11-10-2022")),
                new Product("sp6", 60000, simpleDateFormat.parse("11-10-2023"))
        ));
    }
}
