package com.example.coinhub.service;

import com.example.coinhub.dto.CoinBuyDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

//@Disabled
@EnableFeignClients
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
            Double coinAmount = CommonMarketService.calculateSellFee(amountList.get(coin), 0.0004);
            Double totalCoin = 0D;
            for (Double price : coinOrderBook.keySet()) {
                Double quantity = coinOrderBook.get(price);
                totalCoin += quantity;
            }
            assertThat(totalCoin).isEqualTo(coinAmount);
        }

    }

    @Test
    void calculateTransferFee() throws IOException {
        Map<String, Double> transferFee = bithumbMarketService.calculateTransferFee();
        assertThat(transferFee).isNotEmpty();
    }


}