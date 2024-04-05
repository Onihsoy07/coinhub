package com.example.coinhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinBuyDto {

    private Map<String, Double> amounts;
    private Map<String, Map<Double, Double>> orderBooks;

}
