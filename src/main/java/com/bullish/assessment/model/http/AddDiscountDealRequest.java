package com.bullish.assessment.model.http;

import lombok.Data;

@Data
public class AddDiscountDealRequest {
    private String productId;
    private int quantity;
    private double price;
}
