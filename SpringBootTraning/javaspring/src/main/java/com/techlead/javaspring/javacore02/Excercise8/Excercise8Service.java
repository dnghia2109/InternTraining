package com.techlead.javaspring.javacore02.Excercise8;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

// Bài tập 8: Quản lý danh sách sản phẩm Hãy tạo một ứng dụng quản lý danh sách sản phẩm sử dụng HashMap.
// Mỗi sản phẩm có một mã sản phẩm làm key và thông tin sản phẩm làm value (ví dụ: tên sản phẩm, giá, số lượng).
// Hãy cung cấp các chức năng sau:
// Thêm sản phẩm mới vào danh sách.
// Hiển thị danh sách tất cả các sản phẩm.
// Tìm thông tin sản phẩm dựa trên mã sản phẩm.
// Xóa sản phẩm khỏi danh sách dựa trên mã sản phẩm.
// Cập nhật thông tin sản phẩm.
@Service
public class Excercise8Service {
    public static Scanner sc = new Scanner(System.in);
    public static Map<Integer, Product> productList = new HashMap<>();
//    public static void main(String[] args) {
////        initData();
////        System.out.println(productList);
////        addProduct();
////        System.out.println(productList);
////        findProductById(5);
////        updateProduct(1);
////        System.out.println(productList);
////        deleteProductById(1);
////        System.out.println(productList);
//    }

    public Excercise8Service() {
        initData();
    }

    public Map<Integer, Product> getAllProducts() {
        return productList;
    }

    public static void initData() {
        Product product1 = new Product("sp1", 20000, 20);
        Product product2 = new Product("sp2", 305000, 10);
        Product product3 = new Product("sp3", 20000, 5);

        productList.put(product1.getId(), product1);
        productList.put(product2.getId(), product2);
        productList.put(product3.getId(), product3);
    }

    public void addProduct(ProductRequest productRequest) {
        Product newProduct = new Product();
        newProduct.setName(productRequest.getName());
        newProduct.setQuantity(productRequest.getQuantity());
        newProduct.setPrice(productRequest.getPrice());
        productList.put(newProduct.getId(), newProduct);
    }

    public Product getProductById(int id) {
        Optional<Product> result = Optional.ofNullable(productList.get(id));
        if (result.isEmpty()) {
            throw new RuntimeException("Product with id-" + id + "doesn't exist");
        }
        Product findProduct = result.get();
        return findProduct;
    }

    public Product updateProduct(int id, ProductRequest productRequest) {
        Optional<Product> result = Optional.ofNullable(productList.get(id));
        if (result.isEmpty()) {
            throw new RuntimeException("Product with id-" + id + "doesn't exist");
        }
        Product findProduct = result.get();
        findProduct.setName(productRequest.getName());
        findProduct.setPrice(productRequest.getPrice());
        findProduct.setQuantity(productRequest.getQuantity());

        return findProduct;
    }

    public void deleteProductById(int id) {
        Optional<Product> result = Optional.ofNullable(productList.get(id));
        if (result.isEmpty()) {
            throw new RuntimeException("Product with id-" + id + "doesn't exist");
        }
        productList.remove(id);
    }

//    public static void addProduct() {
//        System.out.println("=== Nhập thông tin sản phẩm mới ===");
//        System.out.println("- Nhập tên sản phẩm:");
//        String productName = sc.nextLine();
//        System.out.println("- Nhập giá sản phẩm:");
//        int productPrice = Integer.parseInt(sc.nextLine());
//        System.out.println("- Nhập số lượng sản phẩm:");
//        int productQuantity = Integer.parseInt(sc.nextLine());
//
//        Product newProduct = new Product(productName, productPrice, productQuantity);
//        // Id tự tăng nên ko cần checl key(productId) đã tổn tại hay chưa
//        productList.put(newProduct.getId(), newProduct);
//    }

//    public static boolean checkProductExist(int id) {
//        if (productList.containsKey(id)) {
//            return true;
//        } else {
//            System.out.println("Không tìm thấy sp có id - " + id);
//            return false;
//        }
//    }
//
//    public static void findProductById(int id) {
//        if (checkProductExist(id)) {
//            System.out.println("- Thông tin sp có id - " + id + ":");
//            System.out.println(productList.get(id));;
//        }
//
//    }
//
//    public static void updateProduct1(int id) {
//        if (checkProductExist(id)) {
//            Product curProduct = productList.get(id);
//            System.out.println("- Nhập tên sản phẩm:");
//            String productName = sc.nextLine();
//            System.out.println("- Nhập giá sản phẩm:");
//            int productPrice = Integer.parseInt(sc.nextLine());
//            System.out.println("- Nhập số lượng sản phẩm:");
//            int productQuantity = Integer.parseInt(sc.nextLine());
//
//            curProduct.setName(productName);
//            curProduct.setQuantity(productQuantity);
//            curProduct.setPrice(productPrice);
//
//            System.out.println("Sp mới cập nhật - " + curProduct);
//        }
//    }
//
//    public static void deleteProductById1(int id) {
//        if (checkProductExist(id)) {
//            productList.remove(id);
//            System.out.println("Xóa thành công sp có id - " + id);
//        }
//    }



}


