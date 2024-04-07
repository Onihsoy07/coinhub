package com.example.coinhub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpbitOrderBookInfo {

    private String market;

    private String timestamp;

    @JsonProperty("total_ask_size")
    private double totalAskSize;

    @JsonProperty("total_bid_size")
    private double totalBidSize;

    @JsonProperty("orderbook_units")
    private List<UpbitOrderBookUnit> orderbookUnits;

    private int level;

}
