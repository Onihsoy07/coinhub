package com.example.coinhub.service.constant;

import java.util.Map;

public class UpbitConstant {

    // 업비트 KRW 마켓 매수 매도 수수료 0.04%
    public static final Double UPBIT_FEE = 0.04 / 100;

    // 코인 출금 수수료(코인 개수)
    public static final Map<String, Double> FEE_LIST = Map.of(
            "BTC", 0.0009,
            "ETH", 0.01,
            "ETC", 0.01,
            "XRP", 1.0
    );

}
