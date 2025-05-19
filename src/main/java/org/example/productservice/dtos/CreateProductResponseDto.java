package org.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CreateProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;

    @Override
    public String toString() {
        return "CreateProductResponseDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
