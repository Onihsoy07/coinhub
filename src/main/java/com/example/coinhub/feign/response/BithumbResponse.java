package com.example.coinhub.feign.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BithumbResponse<T> {

    private String status;
    private T data;

}
