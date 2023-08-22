package org.example.Excercise12;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


// Bài tập 12: Chúng ta có 1 list/set các các sản phẩm bao gồm các thông tin tên, mã, giá bán, ngày sản xuất. Hãy viết 1 đoạn chương trình để sắp xếp các sản phẩm theo các tiêu chí sau:
// Theo tên
// Theo giá bán
// Theo ngày sản xuất
// Theo giá bán và ngày sản xuất
public class Excercise12 {
    public static List<Product> productList = new ArrayList<>();
    public static void main(String[] args) throws ParseException {
        initData();
//        sortByName();
//        sortByPrice();
//        sortByDate();
        sortByPriceAndDate();
//        String pattern = "yyyy-MM-dd";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//
//        Date date1 = simpleDateFormat.parse("2018-09-09");
//        System.out.println(date1.toString());
    }

    private static void sortByPriceAndDate() {

        productList.sort(Comparator
                .comparingInt(Product::getPrice)
                .thenComparing(Product::getDate)
        );
        System.out.println(productList);
//        Collections.sort(productList, new Comparator<Product>() {
//            @Override
//            public int compare(Product o1, Product o2) {
//                int comparePrice = Integer.compare(o1.getPrice(), o2.getPrice());
//                if (comparePrice != 0) {
//                    return comparePrice;
//                } else {
//                    return o1.getDate().compareTo(o2.getDate());
//                }
//            }
//        });
    }

    private static void sortByDate() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        System.out.println(productList);
    }

    private static void sortByName() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println(productList);
    }

    private static void sortByPrice() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o2.getPrice() - o1.getPrice();
            }
        });
        System.out.println(productList);
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
