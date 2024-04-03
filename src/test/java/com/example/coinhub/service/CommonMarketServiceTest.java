package com.example.coinhub.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

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

    @DisplayName("거래소 코인 정보 가져오기")
    @Test
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
}