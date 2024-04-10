package com.example.coinhub.service;

import com.example.coinhub.dto.CoinBuyDto;
import com.example.coinhub.dto.CoinSellDto;
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
        Map<String, Double> coinTotalAmount = new HashMap<>();

        List<String> convertCommonCoinList = commonCoinList.stream().map((v) -> {
            return v = "KRW-" + v;
        }).toList();
        List<UpbitOrderBookInfo> orderBookList = upbitFeignClient.getOrderBookList(convertCommonCoinList);

        orderBookList.stream().forEach(orderBook -> {
            Double currentMoney = money;
            Double totalBuyCoinAmount = 0D;
            String coin = orderBook.getMarket().substring(4);
            Map<Double, Double> buyList = new HashMap<>();

            for (UpbitOrderBookUnit upbitOrderBookUnit : orderBook.getOrderbookUnits()) {
                Double price = upbitOrderBookUnit.getAskPrice();
                Double quantity = upbitOrderBookUnit.getAskSize();
                Double totalBuyMoney = price * quantity;
                Double availableBuyCoinAmount = currentMoney / price;

                // 현재 호가창에서 모든 현금 소진 가능
                if (totalBuyMoney >= currentMoney) {
                    totalBuyCoinAmount += availableBuyCoinAmount;
                    buyList.put(price, availableBuyCoinAmount);
                    availableBuyList.put(coin, buyList);
                    break;
                }
                // 현재 호가창에서 모든 현금 소진 불가능
                else {
                    currentMoney -= totalBuyMoney;
                    totalBuyCoinAmount += quantity;
                    buyList.put(price, quantity);
                }
            }

            coinTotalAmount.put(coin, totalBuyCoinAmount);
        });

        return new CoinBuyDto(coinTotalAmount, availableBuyList);
    }

    @Override
    public CoinSellDto calculateSell(CoinBuyDto coinBuyDto) {
        Map<String, Double> amountList = coinBuyDto.getAmounts();
        Map<String, Map<Double, Double>> availableSellList = new HashMap<>();

        List<String> convertOrderBookCoinList = amountList.keySet().stream().map(v -> "KRW-" + v).toList();
        List<UpbitOrderBookInfo> orderBookList = upbitFeignClient.getOrderBookList(convertOrderBookCoinList);

        orderBookList.stream().forEach(orderBook -> {
            String coin = orderBook.getMarket().substring(4);
            Double currentCoinAmount = amountList.get(coin);
            Map<Double, Double> sellList = new HashMap<>();

            for (UpbitOrderBookUnit upbitOrderBookUnit : orderBook.getOrderbookUnits()) {
                Double price = upbitOrderBookUnit.getAskPrice();
                Double quantity = upbitOrderBookUnit.getAskSize();

                // 현재 호가창에서 모든 코인 매도 가능
                if (currentCoinAmount <= quantity) {
                    sellList.put(price, currentCoinAmount);
                    break;
                }
                // 현재 호가창에서 모든 코인 매도 불가
                else {
                    currentCoinAmount -= quantity;
                    sellList.put(price, quantity);
                }
            }

            availableSellList.put(coin, sellList);
        });

        return new CoinSellDto(null, availableSellList);
    }

}
