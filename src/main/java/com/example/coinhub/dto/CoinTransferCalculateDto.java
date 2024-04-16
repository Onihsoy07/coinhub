package com.example.coinhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinTransferCalculateDto {

    private CoinBuyDto coinBuyDto;
    private CoinSellDto coinSellDto;

}
