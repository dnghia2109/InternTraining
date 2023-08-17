package com.techlead.javaspring.javacore02.Excercise8;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/java02/ex8")
@AllArgsConstructor
public class ControllerEx8 {
    private Excercise8Service excercise8Service;

    @GetMapping("/get-all-product")
    public ResponseEntity<?> getAllProduct() {
        return ResponseEntity.ok(excercise8Service.getAllProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductDetail(@PathVariable int id) {
        return ResponseEntity.ok(excercise8Service.getProductById(id));
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> addNewProduct(@RequestBody ProductRequest productRequest) {
        excercise8Service.addProduct(productRequest);
        return ResponseEntity.ok("Add new product successful!");
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(excercise8Service.updateProduct(id, productRequest));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        excercise8Service.deleteProductById(id);
        return ResponseEntity.ok("Delete product successful!");
    }
}
