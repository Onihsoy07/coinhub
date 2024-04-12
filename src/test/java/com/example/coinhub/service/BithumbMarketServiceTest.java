package com.example.coinhub.service;

import com.example.coinhub.dto.CoinBuyDto;
import com.example.coinhub.dto.CoinSellDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// WireMock서버용 포트 열기
//@AutoConfigureWireMock(port = 0)
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
        double money = 10000000D;
        double deductFeeMoney = CommonMarketService.calculateBuyFee(money, 0.0004);
        // doubl 값 계산으로 orderBook의 합이 정확한 amount가 나오지 않음. 약간의 오차 범위 설정
        double upperLimit = money * 1.001;
        double lowerLimit = money * 0.999;

        // when
        Map<String, Map<Double, Double>> orderBooks = bithumbMarketService.calculateBuy(commonCoin, money).getOrderBooks();

        // then
        for (String key : orderBooks.keySet()) {
            Map<Double, Double> coinOrderBook = orderBooks.get(key);
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
        double money = 10000000D;
        CoinBuyDto coinBuyDto = bithumbMarketService.calculateBuy(commonCoin, money);
        Map<String, Double> amountList = coinBuyDto.getAmounts();

        // when
        Map<String, Map<Double, Double>> orderBookList = bithumbMarketService.calculateSell(coinBuyDto).getOrderBooks();

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