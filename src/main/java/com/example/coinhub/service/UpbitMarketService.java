package com.example.coinhub.service;

import com.example.coinhub.exception.NotCoinPriceInfoException;
import com.example.coinhub.feign.UpbitFeignClient;
import com.example.coinhub.model.UpbitCoinPriceInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpbitMarketService implements MarketService {

    @Autowired
    private final UpbitFeignClient upbitFeignClient;

    @Override
    public double getCurrentCoinPrice(String coin) {
        Optional<List<UpbitCoinPriceInfo>> currentCoinPriceList = upbitFeignClient.getCurrentCoinPrice("KRW-" + coin.toUpperCase());

        if (currentCoinPriceList.isEmpty()) {
            throw new NotCoinPriceInfoException(coin + " 코인의 가격 정보가 존재하지 않습니다.");
        }

        double currentCoinPrice = currentCoinPriceList.get().get(0).getTradePrice();

        return currentCoinPrice;
    }

}
