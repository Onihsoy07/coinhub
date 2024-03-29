package com.example.coinhub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponseDto<T> {

    private int status;
    private boolean success;
    private String message;
    private T data;

}
