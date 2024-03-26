package com.example.coinhub.feign;

import com.example.coinhub.feign.response.BithumbResponse;
import com.example.coinhub.model.BithumbCoinPriceInfo;
import com.example.coinhub.model.UpbitCoinPriceInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "bithumb", url = "https://api.bithumb.com/public")
public interface BithumbFeignClient {

    @GetMapping("/ticker/{coin}")
    BithumbResponse<BithumbCoinPriceInfo> getCurrentCoinPrice(@PathVariable("coin") String coin);

}
