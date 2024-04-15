package com.example.coinhub.service.constant;

import java.util.Map;

public class BithumbConstant {

    // 빗썸 KRW 마켓 매수 매도 수수료 0.04%
    public static final Double BITHUMB_FEE = 0.04 / 100;

    // 코인 출금 수수료(코인 개수)
    public static final Map<String, Double> FEE_LIST = Map.of(
            "BTC", 0.0008,
            "ETH", 0.009,
            "ETC", 0.005,
            "XRP", 0.4
    );


}
