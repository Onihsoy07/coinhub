package com.example.coinhub.service;

import com.example.coinhub.dto.CoinBuyDto;
import com.example.coinhub.dto.CoinSellDto;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MarketService {

    public double getCurrentCoinPrice(String coin);

    public List<String> getCoinList();

    CoinBuyDto calculateBuy(List<String> commonCoinList, double money);

    CoinSellDto calculateSell(CoinBuyDto coinBuyDto);

    Map<String, Double> calculateTransferFee() throws IOException;

}
