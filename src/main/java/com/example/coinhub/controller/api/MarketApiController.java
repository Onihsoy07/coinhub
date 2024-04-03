package com.example.coinhub.controller.api;

import com.example.coinhub.exception.NotCoinPriceInfoException;
import com.example.coinhub.exception.NotFoundMarketServiceException;
import com.example.coinhub.dto.HttpResponseDto;
import com.example.coinhub.service.CommonMarketService;
import com.example.coinhub.service.MarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MarketApiController {

    private final Map<String, MarketService> marketServices;
    private final CommonMarketService commonMarketService;

    @GetMapping("/price")
    public HttpResponseDto<Double> getCurrentCoinPrice(@RequestParam("market") final String market,
                                                       @RequestParam("coin") final String coin) {
        double currentCoinPrice;
        try {
            currentCoinPrice = commonMarketService.getCurrentCoinPrice(market, coin);
        } catch (NotCoinPriceInfoException | NotFoundMarketServiceException e) {
            log.info("getCurrentCoinPrice", e);
            return new HttpResponseDto(HttpStatus.BAD_REQUEST.value(), false, e.getMessage(), null);
        }

        return new HttpResponseDto(HttpStatus.OK.value(), true, "코인 정보 로드 성공", currentCoinPrice);
    }

    @GetMapping("/coins")
    public HttpResponseDto<List<String>> getCoinList(@RequestParam("market") final String market) {
        MarketService marketService = null;
        List<String> coinList = null;

        try {
            marketService = CommonMarketService.getMarketService(marketServices, market);
        } catch (NotFoundMarketServiceException e) {
            log.info("getCoinList", e);
            return new HttpResponseDto(HttpStatus.BAD_REQUEST.value(), false, e.getMessage(), null);
        }

        coinList = marketService.getCoinList();

        return new HttpResponseDto<>(HttpStatus.OK.value(), true, "거래소 모든 코인 정보 조회 성공", coinList);
    }

    @GetMapping("/common-coins")
    public HttpResponseDto<List<String>> getCommonCoinList(@RequestParam("market1") final String fromMarket,
                                                           @RequestParam("market2") final String toMarket) {
        List<String> commonCoinList = commonMarketService.getCommonCoin(fromMarket, toMarket);

        return new HttpResponseDto<>(HttpStatus.OK.value(), true, "공통 코인 리스트 조회 성공", commonCoinList);
    }

}
