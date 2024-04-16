package com.example.coinhub.service;

import com.example.coinhub.dto.CoinBuyDto;
import com.example.coinhub.dto.CoinSellDto;
import com.example.coinhub.dto.CoinTransferCalculateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoinTransferCalculateService {

    private final Map<String, MarketService> marketServices;
    private final CommonMarketService commonMarketService;

    public CoinTransferCalculateDto calculate(String fromMarket, String toMarket, double money) {
        MarketService fromMarketService = CommonMarketService.getMarketService(marketServices, fromMarket);
        MarketService toMarketService = CommonMarketService.getMarketService(marketServices, toMarket);

        // 두 거래소 공통 코인 추출 후 fromMarket에서 코인 구매 toMarket으로 출금하여 수수료 부과 후 매도 coinSellDto 반환
        List<String> commonCoin = commonMarketService.getCommonCoin(fromMarket, toMarket);
        CoinBuyDto coinBuyDto = fromMarketService.calculateBuy(commonCoin, money);
        CoinBuyDto coinBuyDtoWithdrawFee = fromMarketService.calculateTransferFee(coinBuyDto);
        CoinSellDto coinSellDto = toMarketService.calculateSell(coinBuyDtoWithdrawFee);

        return new CoinTransferCalculateDto(coinBuyDtoWithdrawFee, coinSellDto);
    }


}
