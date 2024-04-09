package com.example.coinhub.service;

import com.example.coinhub.dto.CoinBuyDto;
import com.example.coinhub.exception.NotCoinPriceInfoException;
import com.example.coinhub.feign.UpbitFeignClient;
import com.example.coinhub.model.UpbitCoinPriceInfo;
import com.example.coinhub.model.UpbitOrderBookInfo;
import com.example.coinhub.model.UpbitOrderBookUnit;
import com.example.coinhub.model.UpbitResponseCoinInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<String> getCoinList() {
        Optional<List<UpbitResponseCoinInfo>> coinList = upbitFeignClient.getCoinList();
        List<String> krwCoinList = new ArrayList<>();

        if (coinList.isEmpty()) {
            return null;
        }

        for (UpbitResponseCoinInfo coinInfo : coinList.get()) {
            if (coinInfo.getMarket().startsWith("KRW")) {
                krwCoinList.add(coinInfo.getMarket().substring(4));
            }
        }

        return krwCoinList;
    }

    @Override
    public CoinBuyDto calculateBuy(List<String> commonCoinList, double money) {
        Map<String, Map<Double, Double>> availableBuyList = new HashMap<>();

        List<String> convertCommonCoinList = commonCoinList.stream().map((v) -> {
            return v = "KRW-" + v;
        }).toList();
        List<UpbitOrderBookInfo> orderBookList = upbitFeignClient.getOrderBookList(convertCommonCoinList);

        orderBookList.stream().forEach(orderBook -> {
            Double currentMoney = money;
            String coin = orderBook.getMarket();
            Map<Double, Double> buyList = new HashMap<>();

            for (UpbitOrderBookUnit upbitOrderBookUnit : orderBook.getOrderbookUnits()) {
                Double price = upbitOrderBookUnit.getAskPrice();
                Double quantity = upbitOrderBookUnit.getAskSize();
                Double totalBuyMoney = price * quantity;
                Double availableBuyCoinAmount = currentMoney / price;

                // 현재 호가창에서 모든 현금 소진 가능
                if (totalBuyMoney >= currentMoney) {
                    buyList.put(price, availableBuyCoinAmount);
                    availableBuyList.put(coin, buyList);
                    break;
                }
                // 현재 호가창에서 모든 현금 소진 불가능
                else {
                    currentMoney -= totalBuyMoney;
                    buyList.put(price, quantity);
                }
            }
        });

        return new CoinBuyDto(null, availableBuyList);
    }
}
