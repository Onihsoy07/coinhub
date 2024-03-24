package com.example.coinhub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpbitCoinPriceInfo {

    private String market;

    @JsonProperty("trade_date")
    private String tradeDate;

    @JsonProperty("trade_time")
    private String tradeTime;

    @JsonProperty("trade_date_kst")
    private String tradeDateKst;

    @JsonProperty("trade_time_kst")
    private String tradeTimeKst;

    @JsonProperty("trade_timestamp")
    private long tradeTimestamp;

    @JsonProperty("opening_price")
    private long openingPrice;

    @JsonProperty("high_price")
    private long highPrice;

    @JsonProperty("low_price")
    private long lowPrice;

    @JsonProperty("trade_price")
    private long tradePrice;

    @JsonProperty("prev_closing_price")
    private long prevClosingPrice;

    private String change;

    @JsonProperty("change_price")
    private long changePrice;

    @JsonProperty("change_rate")
    private double changeRate;

    @JsonProperty("signed_change_price")
    private long signedChangePrice;

    @JsonProperty("signed_change_rate")
    private double signedChangeRate;

    @JsonProperty("trade_volume")
    private double tradeVolume;

    @JsonProperty("acc_trade_price")
    private double accTradePrice;

    @JsonProperty("acc_trade_price_24h")
    private double accTradePrice24H;

    @JsonProperty("acc_trade_volume")
    private double accTradeVolume;

    @JsonProperty("acc_trade_volume_24h")
    private double accTradeVolume24H;

    @JsonProperty("highest_52_week_price")
    private long highest52_WeekPrice;

    @JsonProperty("highest_52_week_date")
    private LocalDate highest52_WeekDate;

    @JsonProperty("lowest_52_week_price")
    private long lowest52_WeekPrice;

    @JsonProperty("lowest_52_week_date")
    private LocalDate lowest52_WeekDate;


    private long timestamp;

}
