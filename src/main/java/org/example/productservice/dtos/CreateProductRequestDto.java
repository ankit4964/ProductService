package org.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CreateProductRequestDto {
    private String title;
    private String description;
    private Double price;
    private String category;
    private String imageUrl;

    @Override
    public String toString() {
        return "CreateProductRequestDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
