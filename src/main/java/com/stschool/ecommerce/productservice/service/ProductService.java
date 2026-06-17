package com.stschool.ecommerce.productservice.service;

import com.stschool.ecommerce.productservice.dto.request.ProductRequestDto;
import com.stschool.ecommerce.productservice.dto.response.ProductResponseDto;
import com.stschool.ecommerce.productservice.exception.ProductExistsException;
import com.stschool.ecommerce.productservice.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    ProductResponseDto save(ProductRequestDto requestDto) throws ProductExistsException;
    ProductResponseDto getById(String id) throws ProductNotFoundException;
    List<ProductResponseDto> getAll();
    ProductResponseDto update(String id, ProductRequestDto requestDto) throws ProductNotFoundException;
    void deleteById(ProductRequestDto requestDto) throws ProductNotFoundException;
}
