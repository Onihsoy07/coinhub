package com.example.coinhub.controller.api;

import com.example.coinhub.exception.NotCoinPriceInfoException;
import com.example.coinhub.model.HttpResponseDto;
import com.example.coinhub.service.CommonMarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MarketApiController {

    private final CommonMarketService marketService;

    @GetMapping("/price")
    public HttpResponseDto<Double> getCurrentCoinPrice(@RequestParam("market") final String market,
                                               @RequestParam("coin") final String coin) {
        double currentCoinPrice;
        try {
            currentCoinPrice = marketService.getCurrentCoinPrice(market, coin);
        } catch (NotCoinPriceInfoException e) {
            log.info("getCurrentCoinPrice", e);
            return new HttpResponseDto(HttpStatus.BAD_REQUEST.value(), false, e.getMessage(), null);
        }

        return new HttpResponseDto(HttpStatus.OK.value(), true, "코인 정보 로드 성공", currentCoinPrice);
    }

}
