package org.example.productservice.controllers;

import org.example.productservice.dtos.CreateProductRequestDto;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    public ProductController(@Qualifier("RealProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        System.out.println("Product Id: " + productId);
        return productService.getProduct(productId);
    }

    @GetMapping({"/", ""})
    public List<Product> getAllProducts() throws ProductNotFoundException {
        return this.productService.getProducts();
    }

    @PostMapping({ "/", ""})
    public Product addProduct(@RequestBody CreateProductRequestDto requestDto) throws Exception {
//        throw new Exception("Dummy exception");
        System.out.println("Product in Controller: " + requestDto);
        return this.productService.createProduct(requestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long productId) {
        ResponseEntity<Void> response = this.productService.deleteProduct(productId) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        return response;
    }

}
