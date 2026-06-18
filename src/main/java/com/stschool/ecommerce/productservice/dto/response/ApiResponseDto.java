package com.stschool.ecommerce.productservice.dto.response;

import com.stschool.ecommerce.productservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ApiResponseDto<T> {

    private boolean success;
    private String message;
    private int code;
    private T data;

    /*public String id;
    public String name;
    public String company;
    public Status status;*/
}
