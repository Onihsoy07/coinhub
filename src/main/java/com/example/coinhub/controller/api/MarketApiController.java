package com.example.coinhub.controller.api;

import com.example.coinhub.service.CommonMarketService;
import com.example.coinhub.service.MarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MarketApiController {

    private final CommonMarketService marketService;

    @GetMapping("/price")
    public double getCurrentCoinPrice(@RequestParam("market") final String market,
                                      @RequestParam("coin") final String coin) {
        double currentCoinPrice = marketService.getCurrentCoinPrice(market, coin);

        return currentCoinPrice;
    }

}
