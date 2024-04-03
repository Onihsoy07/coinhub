package com.example.coinhub.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CommonMarketServiceTest {

    @Mock
    private UpbitMarketService upbitMarketService;

    @Mock
    private BithumbMarketService bithumbMarketService;

    private CommonMarketService commonMarketService;

    @BeforeEach
    void setup() {
        commonMarketService = new CommonMarketService(
                Map.of(
                        "upbitMarketService", upbitMarketService,
                        "bithumbMarketService", bithumbMarketService
                )
        );
    }

    @Test
    @DisplayName("거래소 코인 정보 가져오기")
    void getCurrentCoinPrice() {
        // given
        String upbit = "upbit";
        String bithumb = "bithumb";
        String coin = "testCoin";
        double price = 9999999D;

        when(upbitMarketService.getCurrentCoinPrice(coin)).thenReturn(price);
        when(bithumbMarketService.getCurrentCoinPrice(coin)).thenReturn(price);

        // when
        double upbitCoinPrice = commonMarketService.getCurrentCoinPrice(upbit, coin);
        double bithumbCoinPrice = commonMarketService.getCurrentCoinPrice(bithumb, coin);

        // then
        assertThat(upbitCoinPrice).isEqualTo(price);
        assertThat(bithumbCoinPrice).isEqualTo(price);

    }

    @Test
    @DisplayName("거래소 서비스 가져오기")
    void getMarketService() {
        // given
        Map<String, MarketService> marketServices = new HashMap<>();
        marketServices.put("upbitMarketService", upbitMarketService);
        marketServices.put("bithumbMarketService", bithumbMarketService);
        String upbitName1 = "upbit";
        String upbitName2 = "upbit".toUpperCase();
        String bithumbName1 = "bithumb";
        String bithumbName2 = "bithumb".toUpperCase();


        // when
        MarketService upbitMarketService1 = CommonMarketService.getMarketService(marketServices, upbitName1);
        MarketService upbitMarketService2 = CommonMarketService.getMarketService(marketServices, upbitName2);
        MarketService bithumbMarketService1 = CommonMarketService.getMarketService(marketServices, bithumbName1);
        MarketService bithumbMarketService2 = CommonMarketService.getMarketService(marketServices, bithumbName2);

        // then
        assertThat(upbitMarketService1).isEqualTo(upbitMarketService);
        assertThat(upbitMarketService2).isEqualTo(upbitMarketService);
        assertThat(bithumbMarketService1).isEqualTo(bithumbMarketService);
        assertThat(bithumbMarketService2).isEqualTo(bithumbMarketService);

    }

}