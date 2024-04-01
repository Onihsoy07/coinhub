package com.example.coinhub.service;

import com.example.coinhub.exception.NotCoinPriceInfoException;
import com.example.coinhub.feign.BithumbFeignClient;
import com.example.coinhub.feign.response.BithumbResponse;
import com.example.coinhub.model.BithumbCoinPriceInfo;
import com.example.coinhub.model.BithumbAvailableCoin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BithumbMarketService implements MarketService {

    @Autowired
    private final BithumbFeignClient bithumbFeignClient;

    @Override
    public double getCurrentCoinPrice(String coin) {
        BithumbResponse<BithumbCoinPriceInfo> currentCoinPrice = bithumbFeignClient.getCurrentCoinPrice(coin.toUpperCase() + "_KRW");

        if (currentCoinPrice.getData() == null) {
            throw new NotCoinPriceInfoException(coin + " 코인의 가격 정보가 존재하지 않습니다.");
        }

        return Double.parseDouble(currentCoinPrice.getData().getClosingPrice());
    }

    @Override
    public List<String> getCoinList() {
        BithumbResponse<Map<String, BithumbAvailableCoin>> coinList = bithumbFeignClient.getCoinList();
        List<String> krwCoinList = new ArrayList<>();

        coinList.getData().forEach((k,v) -> {
            krwCoinList.add(k);
        });

        return krwCoinList;
    }
}
