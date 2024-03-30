package com.example.coinhub.service;

import com.example.coinhub.exception.NotFoundMarketServiceException;
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

    public double getCurrentCoinPrice(String market, String coin) throws NotFoundMarketServiceException {
        MarketService marketService = getMarketservice(marketServices, market);

        return marketService.getCurrentCoinPrice(coin);
    }

    public static MarketService getMarketservice(Map<String, MarketService> marketServices, String market) {
        for (String key : marketServices.keySet()) {
            if (key.substring(0, market.length()).equals(market.toLowerCase())) {
                return  marketServices.get(key);
            }
        }

        throw new NotFoundMarketServiceException("코인 거래소 " + market + "을 찾을 수 없습니다");
    }

}
