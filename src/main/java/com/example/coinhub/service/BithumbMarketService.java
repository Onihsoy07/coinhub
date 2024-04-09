package com.example.coinhub.service;

import com.example.coinhub.dto.CoinBuyDto;
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
import java.util.HashMap;
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

    @Override
    public CoinBuyDto calculateBuy(List<String> commonCoinList, double money) {
        Map<String, Object> orderBookList = bithumbFeignClient.getOrderBookList().getData();
        Map<String, Map<Double, Double>> availableBuyList = new HashMap<>();

        orderBookList.forEach((k, v) -> {
            if ((!(k.equalsIgnoreCase("timestamp") || k.equalsIgnoreCase("payment_currency"))) && commonCoinList.contains(k)) {
                double currentMoney = money;
                Map<Double, Double> buyList = new HashMap<>();

                String coin = k;
                List<Map<String, String>> asks = (List<Map<String, String>>) ((Map<String, Object>) v).get("asks");

                for (Map<String, String> currentOrderBook  : asks) {
                    Double price = Double.parseDouble(currentOrderBook.get("price"));
                    Double quantity = Double.parseDouble(currentOrderBook.get("quantity"));
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
            }
        });

        return new CoinBuyDto(null, availableBuyList);
    }
}
