package com.example.coinhub.controller.api;

import com.example.coinhub.dto.CoinTransferCalculateDto;
import com.example.coinhub.dto.HttpResponseDto;
import com.example.coinhub.service.CoinTransferCalculateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TransferCalculateApiController {

    private final CoinTransferCalculateService coinTransferCalculateService;

    @GetMapping("/transfer-calculate")
    public HttpResponseDto<CoinTransferCalculateDto> transferCalculateResult(@RequestParam(name = "fromMarket") String fromMarket,
                                                                             @RequestParam(name = "toMarket") String toMarket,
                                                                             @RequestParam(name = "amount") double amount) {

        return new HttpResponseDto<>(HttpStatus.OK.value(), true, "변환 정보 로드 성공", null);
    }

}
