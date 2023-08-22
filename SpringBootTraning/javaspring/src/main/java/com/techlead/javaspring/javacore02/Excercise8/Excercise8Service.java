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
}


