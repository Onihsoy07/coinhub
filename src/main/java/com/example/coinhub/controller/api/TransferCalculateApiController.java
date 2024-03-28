package com.example.coinhub.controller.api;

import com.example.coinhub.view.TransferCalculateView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TransferCalculateApiController {

    @GetMapping("/transfer-calculate")
    public TransferCalculateView transferCalculateResult(@RequestParam(name = "fromMarket") String fromMarket,
                                                         @RequestParam(name = "toMarket") String toMarket,
                                                         @RequestParam(name = "amount") double amount) {
        return new TransferCalculateView("BTC", 123D, Map.of(123D, 123D), Map.of(123D, 123D));
    }

}
