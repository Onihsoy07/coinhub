package com.example.coinhub.service;

import com.example.coinhub.dto.CoinBuyDto;
import com.example.coinhub.feign.BithumbFeignClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// WireMock서버용 포트 열기
@AutoConfigureWireMock(port = 0)
class BithumbMarketServiceTest {

    @Autowired
    MarketService bithumbMarketService;

    @Test
    void calculateBuy() {
        // given
        List<String> commonCoin = List.of(
                "BTC",
                "ETH",
                "ETC"
        );
        double amount = 1000000D;
        // doubl 값 계산으로 orderBook의 합이 정확한 amount가 나오지 않음. 약간의 오차 범위 설정
        double upperLimit = amount * 1.01;
        double lowerLimit = amount * 0.99;

        // when
        Map<String, Map<Double, Double>> orderBooks = bithumbMarketService.calculateBuy(commonCoin, amount).getOrderBooks();

        // then
        for (String key : orderBooks.keySet()) {
            Map<Double, Double> coinOrderBook = orderBooks.get(key);
            double totalPrice = 0D;
            for (Double price : coinOrderBook.keySet()) {
                Double quantity = coinOrderBook.get(price);
                totalPrice += price * quantity;
            }
            assertThat(totalPrice).isGreaterThanOrEqualTo(lowerLimit).isLessThanOrEqualTo(upperLimit);
        }
    }
}