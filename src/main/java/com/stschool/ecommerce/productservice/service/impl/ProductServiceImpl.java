package com.stschool.ecommerce.productservice.service.impl;

import com.stschool.ecommerce.productservice.document.Product;
import com.stschool.ecommerce.productservice.dto.request.ProductRequestDto;
import com.stschool.ecommerce.productservice.dto.response.ProductResponseDto;
import com.stschool.ecommerce.productservice.enums.Status;
import com.stschool.ecommerce.productservice.exception.ProductExistsException;
import com.stschool.ecommerce.productservice.exception.ProductNotFoundException;
import com.stschool.ecommerce.productservice.repository.ProductRepository;
import com.stschool.ecommerce.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductResponseDto save(ProductRequestDto requestDto) throws ProductExistsException {
        productRepository.findByName(requestDto.getName()).ifPresent(p-> {
            throw new ProductExistsException("Product already exists with name: "+requestDto.getName());
        });

        Product product = modelMapper.map(requestDto, Product.class);
        product.setStatus(Status.AVAILABLE);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        return modelMapper.map(productRepository.save(product),ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto getById(String id) throws ProductNotFoundException {
        return modelMapper.map(productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found with id: "+ id)), ProductResponseDto.class);
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll().stream().map(product -> modelMapper.map(product,ProductResponseDto.class)).toList();
    }

    @Override
    public ProductResponseDto update(String id, ProductRequestDto requestDto) throws ProductNotFoundException {
        productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found with id: "+ id));
        return modelMapper.map(productRepository.save(modelMapper.map(requestDto,Product.class)), ProductResponseDto.class);
    }

    @Override
    public void deleteById(String id) throws ProductNotFoundException {
        productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found with id: "+ id));
        productRepository.deleteById(id);
    }
}
