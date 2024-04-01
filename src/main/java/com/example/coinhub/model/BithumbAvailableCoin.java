package com.example.coinhub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BithumbAvailableCoin {

    @JsonProperty("opening_price")
    private String openingPrice;

}
