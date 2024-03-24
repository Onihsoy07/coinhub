package com.example.coinhub.service;

import com.example.coinhub.exception.NotCoinPriceInfoException;
import com.example.coinhub.feign.UpbitFeignClient;
import com.example.coinhub.model.UpbitCoinPriceInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpbitMarketService implements MarketService {

    @Autowired
    private final UpbitFeignClient upbitFeignClient;

    @Override
    public double getCurrentCoinPrice(String coin) {
        List<UpbitCoinPriceInfo> currentCoinPriceList = upbitFeignClient.getCurrentCoinPrice("KRW-" + coin.toUpperCase());
        
        if (currentCoinPriceList == null) {
            throw new NotCoinPriceInfoException(coin + " 코인의 가격 정보가 존재하지 않습니다.");
        }

        double currentCoinPrice = currentCoinPriceList.get(0).getTradePrice();

        return currentCoinPrice;
    }

}
