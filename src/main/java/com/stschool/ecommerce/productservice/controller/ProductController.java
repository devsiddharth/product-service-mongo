package com.stschool.ecommerce.productservice.controller;

import com.stschool.ecommerce.productservice.dto.request.ProductRequestDto;
import com.stschool.ecommerce.productservice.dto.response.ApiResponseDto;
import com.stschool.ecommerce.productservice.dto.response.ProductResponseDto;
import com.stschool.ecommerce.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Builder
@RequestMapping("/api/products")

public class ProductController {
    private final ProductService service;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> save(@RequestBody ProductRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseDto.<ProductResponseDto>builder()
                        .success(true)
                        .message("Product created successfully")
                        .code(HttpStatus.CREATED.value())
                        .data(service.save(requestDto))
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getAll(){
        return ResponseEntity.ok(
                ApiResponseDto.<List<ProductResponseDto>>builder()
                        .success(true)
                        .message("Retrieved all  the Products")
                        .code(HttpStatus.OK.value())
                        .data(service.getAll())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> getById(@PathVariable String id){
        return ResponseEntity.ok(
                ApiResponseDto.<ProductResponseDto>builder()
                        .success(true)
                        .message("Product with Id is retrieved successfully")
                        .code(HttpStatus.OK.value())
                        .data(service.getById(id))
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> updateById(@PathVariable String id, @RequestBody ProductRequestDto requestDto){
        return ResponseEntity.ok(
                ApiResponseDto.<ProductResponseDto>builder()
                        .success(true)
                        .message("Product updated successfully with id: "+ id)
                        .code(HttpStatus.OK.value())
                        .data(service.update(id, requestDto))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteById(@PathVariable String id){
        service.deleteById(id);
        return  ResponseEntity.ok(
                ApiResponseDto.<Void>builder()
                        .success(true)
                        .message("Product deleted successfully")
                        .code(HttpStatus.OK.value())
                        .build()
        );
    }


}
