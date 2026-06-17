package com.stschool.ecommerce.productservice.document;

import com.stschool.ecommerce.productservice.enums.Category;
import com.stschool.ecommerce.productservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")

public class Product {
    @Id
    public String id;
    public String name;
    public int maxRetailPrice;
    private float discountPercentage;
    public Category category;
    public String company;
    public Status status;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
}
