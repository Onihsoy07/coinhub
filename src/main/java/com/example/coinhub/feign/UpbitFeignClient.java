package com.example.coinhub.feign;

import com.example.coinhub.model.UpbitCoinPriceInfo;
import com.example.coinhub.model.UpbitResponseCoinInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "upbit", url = "https://api.upbit.com/v1", dismiss404 = true)
public interface UpbitFeignClient {

    @GetMapping("/ticker")
    Optional<List<UpbitCoinPriceInfo>> getCurrentCoinPrice(@RequestParam("markets") String coin);

    @GetMapping("/market/all")
    Optional<List<UpbitResponseCoinInfo>> getCoinList();

}
