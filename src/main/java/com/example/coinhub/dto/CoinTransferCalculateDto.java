package com.example.coinhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinTransferCalculateDto {

    private String coin;
    private double amount;
    private Map<Double, Double> buyOrderBook;
    private Map<Double, Double> sellOrderBook;

}
