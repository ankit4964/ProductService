package org.example.productservice.services;

import lombok.AllArgsConstructor;
import org.example.productservice.dtos.CreateProductRequestDto;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.repositories.CategoryRepository;
import org.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("RealProductService")
@AllArgsConstructor
public class RealProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public Product createProduct(CreateProductRequestDto requestDto) {
        System.out.println("Product received: " + requestDto);
        String categoryTitle = requestDto.getCategory();
        Optional<Category> oCategory = this.categoryRepository.findByTitle(categoryTitle);
        Category category = null;
        if(oCategory.isPresent()) {
            category = oCategory.get();
        } else {
            category = new Category();
            category.setTitle(categoryTitle);
            category = this.categoryRepository.save(category);
        }
        Product product = new Product();
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        product.setDescription(requestDto.getDescription());
        product.setCategory(category);
        product.setImageUrl(requestDto.getImageUrl());
        product = this.productRepository.save(product);
        System.out.println("Saved product: " + product);
        return product;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }

    @Override
    public Product getProduct(Long productId) throws ProductNotFoundException {
        return this.productRepository.findById(productId).orElseThrow(() ->
            new ProductNotFoundException("Product not found", productId)
        );
    }

    @Override
    public List<Product> getProducts() throws ProductNotFoundException {
        return this.productRepository.findAll();
    }

}
