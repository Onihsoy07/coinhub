package com.example.coinhub.service;

import com.example.coinhub.dto.CoinBuyDto;
import com.example.coinhub.dto.CoinSellDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureWireMock(port = 0)
class UpbitMarketServiceTest {

    @Autowired
    MarketService upbitMarketService;

    @Test
    void calculateBuy() {
        // given
        List<String> commonCoin = List.of(
                "BTC",
                "ETH",
                "ETC"
        );
        double money = 100000000D;
        double deductFeeMoney = CommonMarketService.calculateBuyFee(money, 0.0004);
        // doubl 값 계산으로 orderBook의 합이 정확한 amount가 나오지 않음. 약간의 오차 범위 설정
        double upperLimit = money * 1.001;
        double lowerLimit = money * 0.999;

        // when
        Map<String, Map<Double, Double>> orderBookList = upbitMarketService.calculateBuy(commonCoin, money).getOrderBooks();

        // then
        for (String coin : orderBookList.keySet()) {
            Map<Double, Double> coinOrderBook = orderBookList.get(coin);
            double totalPrice = 0D;
            for (Double price : coinOrderBook.keySet()) {
                Double quantity = coinOrderBook.get(price);
                totalPrice += price * quantity;
            }
            assertThat(totalPrice).isEqualTo(deductFeeMoney);
        }

    }

    @Test
    void calculateSell() {
        // given
        List<String> commonCoin = List.of(
                "BTC",
                "ETH",
                "ETC"
        );
        double money = 100000000D;
        CoinBuyDto coinBuyDto = upbitMarketService.calculateBuy(commonCoin, money);
        Map<String, Double> amountList = coinBuyDto.getAmounts();

        // when
        CoinSellDto coinSellDto = upbitMarketService.calculateSell(coinBuyDto);
        Map<String, Map<Double, Double>> orderBookList = coinSellDto.getOrderBooks();

        //then
        for (String coin : orderBookList.keySet()) {
            Map<Double, Double> coinOrderBook = orderBookList.get(coin);
            Double coinAmount = amountList.get(coin);
            Double totalCoin = 0D;
            for (Double price : coinOrderBook.keySet()) {
                Double quantity = coinOrderBook.get(price);
                totalCoin += quantity;
            }

            assertThat(totalCoin).isEqualTo(coinAmount);
        }

    }


}