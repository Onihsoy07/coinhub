package com.example.coinhub.service;

import com.example.coinhub.dto.CoinDto;

import java.util.List;

public interface MarketService {

    public double getCurrentCoinPrice(String coin);

    public List<String> getCoinList();

}
