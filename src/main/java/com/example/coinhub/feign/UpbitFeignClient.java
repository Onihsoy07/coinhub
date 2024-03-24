package com.example.coinhub.feign;

import com.example.coinhub.model.UpbitCoinPriceInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "upbit", url = "https://api.upbit.com/v1")
public interface UpbitFeignClient {

    @GetMapping("/ticker")
    List<UpbitCoinPriceInfo> getCurrentCoinPrice(@RequestParam("markets") String coin);

}
