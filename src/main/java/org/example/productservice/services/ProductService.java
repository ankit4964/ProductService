package org.example.productservice.services;

import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(Long productId) throws ProductNotFoundException;
    List<Product> getProducts() throws ProductNotFoundException;
    Product createProduct(Product product);
    boolean deleteProduct(Long productId);
}
