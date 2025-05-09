package org.example.productservice.services;

import org.example.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(Long productId);
    List<Product> getProducts();
    Product createProduct(Product product);
    boolean deleteProduct(Long productId);
}
