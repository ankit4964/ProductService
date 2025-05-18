package org.example.productservice.controllers;

import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long productId) {
        try {
            System.out.println("Product Id: " + productId);
            return productService.getProduct(productId);
        } catch(ProductNotFoundException e) {
            System.out.println("Product not found");
            return null;
        }
    }

    @GetMapping({"/", ""})
    public List<Product> getAllProducts() {
        try {
            return this.productService.getProducts();
        } catch(ProductNotFoundException e) {
            System.out.println("Product not found");
            return null;
        }
    }

    @PostMapping({ "/", ""})
    public Product addProduct(Product product) {
        return this.productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long productId) {
        ResponseEntity<Void> response = this.productService.deleteProduct(productId) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        return response;
    }

}
