package com.example.coinhub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonMarketService {

    private final Map<String, MarketService> marketServices;

    public double getCurrentCoinPrice(String market, String coin) {
        MarketService marketService = null;
        double currentCoinPrice;

        for (String key : marketServices.keySet()) {
            if (key.substring(0, market.length()).equals(market.toLowerCase())) {
                marketService = marketServices.get(key);
                break;
            }
        }

        currentCoinPrice = marketService.getCurrentCoinPrice(coin);

        return currentCoinPrice;
    }


}
