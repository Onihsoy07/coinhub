package com.example.coinhub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BithumbCoinPriceInfo {

    @JsonProperty("opening_price")
    private String openingPrice;

    @JsonProperty("closing_price")
    private String closingPrice;

    @JsonProperty("min_price")
    private String minPrice;

    @JsonProperty("max_price")
    private String maxPrice;

    @JsonProperty("units_traded")
    private String unitsTraded;

    @JsonProperty("acc_trade_value")
    private String accTradeValue;

    @JsonProperty("prev_closing_price")
    private String prevClosingPrice;

    @JsonProperty("units_traded_24H")
    private String unitsTraded24H;

    @JsonProperty("acc_trade_value_24H")
    private String accTradeValue24H;

    @JsonProperty("fluctate_24H")
    private String fluctate24H;

    @JsonProperty("fluctate_rate_24H")
    private String fluctateRate24H;

    @JsonProperty("date")
    private String date;

}
