package org.example.productservice.exceptions;

public class ProductNotFoundException extends Exception {
    private Long productId;
    public ProductNotFoundException(String message) {
        super(message);
    }
    public ProductNotFoundException(String message, Long productId) {
        super(message);
        this.productId = productId;
    }
}
