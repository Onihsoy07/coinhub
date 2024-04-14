package com.example.coinhub.service;

import com.example.coinhub.dto.CoinTransferCalculateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoinTransferCalculateService {

    private final Map<String, MarketService> marketServices;

    public CoinTransferCalculateDto calculate(String fromMarket, String toMarket, double amount) {
        MarketService fromMarketService = CommonMarketService.getMarketService(marketServices, fromMarket);
        MarketService toMarketService = CommonMarketService.getMarketService(marketServices, toMarket);


        return new CoinTransferCalculateDto(
                null,
                0D,
                null,
                null
        );
    }
}
