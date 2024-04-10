package com.example.coinhub.service;

import com.example.coinhub.dto.CoinBuyDto;
import com.example.coinhub.dto.CoinSellDto;

import java.util.List;

public interface MarketService {

    public double getCurrentCoinPrice(String coin);

    public List<String> getCoinList();

    CoinBuyDto calculateBuy(List<String> commonCoinList, double money);

    CoinSellDto calculateSell(CoinBuyDto coinBuyDto);

}
