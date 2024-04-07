package com.example.coinhub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpbitOrderBookUnit {

    @JsonProperty("ask_price")
    private double askPrice;

    @JsonProperty("bid_price")
    private double bidPrice;

    @JsonProperty("ask_size")
    private double askSize;

    @JsonProperty("bid_size")
    private double bidSize;

}
