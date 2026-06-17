package com.stschool.ecommerce.productservice.repository;

import com.stschool.ecommerce.productservice.document.Product;
import com.stschool.ecommerce.productservice.dto.request.ProductRequestDto;
import com.stschool.ecommerce.productservice.dto.response.ProductResponseDto;
import com.stschool.ecommerce.productservice.exception.ProductNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ProductRepository extends MongoRepository<Product, String> {
     Optional<Product> findByName(String name);
}
