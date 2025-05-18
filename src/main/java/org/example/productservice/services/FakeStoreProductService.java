package org.example.productservice.services;

import org.example.productservice.dtos.FakeStoreProductDto;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProduct(Long productId) throws ProductNotFoundException {
        String url = "https://fakestoreapi.com/products/1";
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity(url, FakeStoreProductDto.class);
        System.out.println("Status : " + fakeStoreProductDtoResponseEntity.getStatusCode() + " | " + fakeStoreProductDtoResponseEntity.getBody());
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();
        Product product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        System.out.println("Response : " + product);
        return product;
    }

    @Override
    public List<Product> getProducts() throws ProductNotFoundException {
        String url = "https://fakestoreapi.com/products";
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity(url, FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();
        if(fakeStoreProductDto == null || fakeStoreProductDto.length == 0) {
            throw new ProductNotFoundException("No products founds!");
        }
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto1 : fakeStoreProductDto) {
            Product product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto1);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        if(fakeStoreProductDto == null) {
            return new Product();
        }
        Product product = new Product();
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setId(fakeStoreProductDto.getId());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

}
